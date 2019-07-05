package kensyuJuly3rd.problem3;

import java.sql.SQLException;

/**
 * 更新処理を集めたクラス(UPDATEタイプのSQL文)
 * @author k_oda
 *
 */

public class DBUpdate extends DBFields{

	/**
	 * 運勢名が書き換えられていた時の更新処理メソッド(DBRelationで使用)
	 *
	 * SQL文：UPDATE fortune SET fortune_name = fortuneName, updater = updater(引数), update_day = today WHERE fortune_id = i
	 * SQL文の動作：運勢テーブルの指定の行の運勢名、更新者、更新日を更新
	 * @param negaigoto	新しい運勢名
	 * @param updater	更新者
	 * @param today		更新日
	 * @param fortuneId	setOmikujiAndFortuneTableメソッド内で設定された運勢コード
	 * @throws SQLException
	 */
	protected static void updateFortuneName(String fortuneName, String updater, String today, int fortuneId) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE fortune SET fortune_name = '");
		sb.append(fortuneName);
		sb.append("', updater = '");
		sb.append(updater);
		sb.append("', update_day = '");
		sb.append(today);
		sb.append("' WHERE fortune_id = ");
		sb.append(fortuneId);
		String sql = sb.toString();
		stmt.executeUpdate(sql);
	}

	/**
	 * 運勢コードが書き換えられていた時の更新処理メソッド(DBRelationで使用)
	 *
	 * SQL文：UPDATE omikuji SET fortune_id = fortuneId, updater = updater(引数), update_day = today WHERE omikuji_id = i
	 * SQL文の動作：おみくじテーブルの指定の行の運勢コード、更新者、更新日を更新
	 * @param fortuneCode	新しい運勢コード
	 * @param updater		更新者
	 * @param today			更新日
	 * @param i				更新する行を指定するためのsetOmikujiAndFortuneTableメソッド内のループカウンタ
	 * @throws SQLException
	 */
	protected static void updateFortuneId(int fortuneId, String updater, String today, int i) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE omikuji SET fortune_id = ");
		sb.append(fortuneId);
		sb.append(", updater = '");
		sb.append(updater);
		sb.append("', update_day = '");
		sb.append(today);
		sb.append("' WHERE omikuji_id = ");
		sb.append(i);
		String sql = sb.toString();
		stmt.executeUpdate(sql);
	}

	/**
	 * 願い事が書き換えられていた時の更新処理メソッド(DBRelationで使用)
	 *
	 * SQL文：UPDATE omikuji SET negaigoto = negaigoto(引数), updater = updater(引数), update_day = today WHERE omikuji_id = i
	 * SQL文の動作：おみくじテーブルの指定の行の願い事、更新者、更新日を更新
	 * @param negaigoto	新しい願い事
	 * @param updater	更新者
	 * @param today		更新日
	 * @param i			更新する行を指定するためのsetOmikujiAndFortuneTableメソッド内のループカウンタ
	 * @throws SQLException
	 */
	protected static void updateNegaigoto(String negaigoto, String updater, String today, int i) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE omikuji SET negaigoto = '");
		sb.append(negaigoto);
		sb.append("', updater = '");
		sb.append(updater);
		sb.append("', update_day = '");
		sb.append(today);
		sb.append("' WHERE omikuji_id = ");
		sb.append(i);
		String sql = sb.toString();
		stmt.executeUpdate(sql);
	}

	/**
	 * 商いが書き換えられていた時の更新処理メソッド(DBRelationで使用)
	 *
	 * SQL文：UPDATE omikuji SET akinai = akinai(引数), updater = updater(引数), update_day = today WHERE omikuji_id = i
	 * SQL文の動作：おみくじテーブルの指定の行の商い、更新者、更新日を更新
	 * @param akinai	新しい商い
	 * @param updater	更新者
	 * @param today		更新日
	 * @param i			更新する行を指定するためのsetOmikujiAndFortuneTableメソッド内のループカウンタ
	 * @throws SQLException
	 */
	protected static void updateAkinai(String akinai, String updater, String today, int i) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE omikuji SET akinai = '");
		sb.append(akinai);
		sb.append("', updater = '");
		sb.append(updater);
		sb.append("', update_day = '");
		sb.append(today);
		sb.append("' WHERE omikuji_id = ");
		sb.append(i);
		String sql = sb.toString();
		stmt.executeUpdate(sql);
	}

	/**
	 * 学問が書き換えられていた時の更新処理メソッド(DBRelationで使用)
	 *
	 * SQL文：UPDATE omikuji SET gakumon = gakumon(引数), updater = updater(引数), update_day = today WHERE omikuji_id = i
	 * SQL文の動作：おみくじテーブルの指定の行の学問、更新者、更新日を更新
	 * @param gakumon	新しい学問
	 * @param updater	更新者
	 * @param today		更新日
	 * @param i			更新する行を指定するためのsetOmikujiAndFortuneTableメソッド内のループカウンタ
	 * @throws SQLException
	 */
	protected static void updateGakumon(String gakumon, String updater, String today, int i) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE omikuji SET gakumon = '");
		sb.append(gakumon);
		sb.append("', updater = '");
		sb.append(updater);
		sb.append("', update_day = '");
		sb.append(today);
		sb.append("' WHERE omikuji_id = ");
		sb.append(i);
		String sql = sb.toString();
		stmt.executeUpdate(sql);
	}
}
