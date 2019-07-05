package kensyuJuly3rd.problem3;

import java.sql.SQLException;

/**
 * 検索処理を集めたクラス(SELECTタイプのSQL文)
 * @author k_oda
 *
 */
public class DBSelect extends DBFields{

	/**
	 * 運勢テーブルに情報を追加するかを判別するための条件用メソッド(DBRelationで使用)
	 *
	 * SQL文：SELECT fortune_name FROM fortune WHERE fortune_id =  fortuneId
	 * SQL文の動作：運勢テーブルで引数であるIDから運勢名を取得
	 * if文の条件：指定の行が存在するかどうか(あった場合はif、なかった場合はelseに入る)
	 *
	 * @param fortuneId	setOmikujiAndFortuneTableメソッド内で設定された運勢コード
	 * @return	true(指定の運勢コードがなかった時)/false(指定の運勢コードがあった時)
	 * @throws SQLException
	 */
	protected static boolean getFortuneId(int fortuneId) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT fortune_name FROM fortune WHERE fortune_id = ");
		sb.append(fortuneId);
		String sql = sb.toString();
		rset = stmt.executeQuery(sql);

		if(rset.next()) {
			return false;
		}else {
			return true;
		}
	}

	/**
	 * 運勢テーブルの中身が書き換えられていないかチェックするメソッド(DBRelationで使用)
	 *
	 * SQL文：SELECT fortune_name FROM fortune WHERE fortune_id = fortuneId
	 * SQL文の動作：運勢テーブルで引数であるiが運勢IDとなりそこから運勢名を取得
	 * if文の条件：指定の行が存在するかどうか(あった場合はif、なかった場合はelseに入る)
	 *
	 * @param fortuneId setOmikujiAndFortuneTableメソッド内で設定された運勢コード
	 * @return rset.getString("fortune_name")(指定の行があった場合、運勢名を返す)/null(指定の行がなかった場合)
	 * @throws SQLException
	 */
	protected static String checkFortuneName(int fortuneId) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT fortune_name FROM fortune WHERE fortune_id = ");
		sb.append(fortuneId);
		String sql = sb.toString();
		rset = stmt.executeQuery(sql);

		if(rset.next()) {
			return rset.getString("fortune_name");
		} else {
			return null;
		}
	}

	/**
	 * おみくじテーブルに情報を追加するかを判別するための条件用メソッド(DBRelationで使用)
	 *
	 * SQL文：SELECT * FROM omikuji WHERE omikuji_id = i
	 * SQL文の動作：おみくじテーブルで引数であるiがおみくじIDとなりそこから1行文の情報を取得
	 * if文の条件：指定の行が存在するかどうか(あった場合はif、なかった場合はelseに入る)
	 *
	 * @param i setOmikujiAndFortuneTableメソッド内のループカウンタ
	 * @return	true(指定のおみくじコードがなかった時)/false(指定のおみくじコードがあった時)
	 * @throws SQLException
	 */
	protected static boolean getOmikujiTable(int i) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM omikuji WHERE omikuji_id = ");
		sb.append(i);
		String sql = sb.toString();
		rset = stmt.executeQuery(sql);

		if(rset.next()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * おみくじテーブルの中身が書き換えられていないかチェックするメソッド(DBRelationで使用)
	 *
	 * SQL文：SELECT * FROM omikuji WHERE omikuji_id = 「引数」
	 * SQL文の動作：おみくじテーブルで引数であるiがおみくじIDとなりそこから1行文の情報を取得
	 * if文の条件：指定の行が存在するかどうか(あった場合はif、なかった場合はelseに入る)
	 *
	 * @param i setOmikujiAndFortuneTableメソッド内のループカウンタ
	 * @return unsei(指定の行があった場合の運勢コード、願い事、商い、学問を格納した配列)/null(指定の行がなかった場合)
	 * @throws SQLException
	 */
	protected static String[] checkOmikujiTable(int i) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM omikuji WHERE omikuji_id = ");
		sb.append(i);
		String sql = sb.toString();
		rset = stmt.executeQuery(sql);

		if(rset.next()) {
			int fortuneCode = rset.getInt("fortune_id");
			String fortuneId = String.valueOf(fortuneCode);
			String[] unsei = new String[4];
			unsei[0] = fortuneId;
			unsei[1] = rset.getString("negaigoto");
			unsei[2] = rset.getString("akinai");
			unsei[3] = rset.getString("gakumon");
			return unsei;
		} else {
			return null;
		}
	}

	/**
	 * 以前に表示されたおみくじの情報を返すメソッド(メインクラスで使用)
	 *
	 *SQL文：SELECT omikuji_id FROM result WHERE fortune_day = today AND birthday = birthday(引数)
	 * SQL文の動作：結果テーブルから引数である実行日と入力誕生日を元におみくじIDを取得
	 * @param today	実行日
	 * @param bday	入力された誕生日
	 * @return	rset.getInt("omikuji_id")	おみくじコード(ない場合はnullが返る)
	 * @throws SQLException
	 */
	public static Integer getPastOmikuji(String today, String birthday) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT omikuji_id FROM result WHERE fortune_day = '");
		sb.append(today);
		sb.append("' AND birthday = '");
		sb.append(birthday);
		sb.append("'");
		String sql = sb.toString();
		rset = stmt.executeQuery(sql);
		if(rset.next()) {
			return rset.getInt("omikuji_id");
		} else {
			return null;
		}
	}

	/**
	 * おみくじテーブルの行数を返すメソッド(メインクラスで使用)
	 *
	 * SQL文：SELECT COUNT(*) FROM omikuji
	 * SQL文の動き：おみくじテーブルの行数を数える
	 * @return	rset.getInt("count") おみくじテーブルの行数
	 * @throws SQLException
	 */
	public static int getOmikujiCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM omikuji";
		rset = stmt.executeQuery(sql);
		rset.next();
		return rset.getInt("count");
	}
	/**
	 * おみくじテーブルから情報をもらうメソッド(メインクラスで使用)
	 *
	 *SQL文：SELECT f.fortune_name, o.negaigoto, o.akinai, o.gakumon FROM fortune f INNER JOIN omikuji o
	 *			ON f.fortune_id = o.fortune_id WHERE omikuji_id = omikujiId
	 *SQL文の動作：内部結合した運勢テーブルとおみくじテーブルから引数のおみくじIDを元に運勢名、願い事、商い、学問を取得
	 *DBから取得した情報をおみくじ型変数に格納
	 *
	 * @param omikujiId	SQL文のWHERE句に入るおみくじID
	 * @return omikuji	DBから取得した情報が格納されたOmikuji型変数
	 * @throws SQLException
	 */
	public static Omikuji getOmikuji(int omikujiId) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT f.fortune_name, o.negaigoto, o.akinai, o.gakumon FROM fortune f INNER JOIN omikuji o ");
		sb.append("ON f.fortune_id = o.fortune_id WHERE omikuji_id = ");
		sb.append(omikujiId);
		String sql = sb.toString();
		rset = stmt.executeQuery(sql);
		rset.next();

		Omikuji omikuji = null;
		switch(rset.getString("fortune_name")) {
		case "大吉":
			omikuji = new Daikichi();
			break;

		case "中吉":
			omikuji = new Chukichi();
			break;

		case "小吉":
			omikuji = new Syokichi();
			break;

		case "末吉":
			omikuji = new Suekichi();
			break;

		case "吉":
			omikuji = new Kichi();
			break;

		case "凶":
			omikuji = new Kyo();
			break;
	}
		omikuji.setUnsei();
		omikuji.setNegaigoto(rset.getString("negaigoto"));
		omikuji.setAkinai(rset.getString("akinai"));
		omikuji.setGakumon(rset.getString("gakumon"));

		return omikuji;
	}
}
