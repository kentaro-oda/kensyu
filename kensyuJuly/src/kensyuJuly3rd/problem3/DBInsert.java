package kensyuJuly3rd.problem3;

import java.sql.SQLException;

/**
 * 登録処理を集めたクラス(INSERTタイプのSQL文)
 * @author k_oda
 *
 */
public class DBInsert extends DBFields {

	/**
	 * 運勢テーブルに情報を登録するメソッド(DBRelationで使用)
	 *
	 * SQL文：INSERT INTO fortune(fortune_id,fortune_name, creater, create_day) VALUES (fortuneId, fortuneList[0], userName, today)
	 * SQL文の動作：運勢テーブルに運勢コード、運勢名、作成者、作成日を登録
	 *
	 * @param fortuneId		運勢コード
	 * @param fortuneName	運勢名
	 * @param creater		作成者
	 * @param createDay		作成日
	 * @throws SQLException
	 */
	protected static void insertFortuneTable(int fortuneId, String fortuneName, String creater, String createDay) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO fortune(fortune_id,fortune_name, creater, create_day) VALUES (");
		sb.append(fortuneId);
		sb.append(", '");
		sb.append(fortuneName);
		sb.append("', '");
		sb.append(creater);
		sb.append("', '");
		sb.append(createDay);
		sb.append("')");
		String sql = sb.toString();
		stmt.executeUpdate(sql);
	}

	/**
	 * おみくじテーブルに情報を登録するメソッド(DBRelationで使用)
	 *
	 * SQL文：INSERT INTO omikuji(omikuji_id, fortune_id, negaigoto, akinai, gakumon, creater, create_day)
	 * 			VALUES (i, fortuneId, fortuneList[1], fortuneList[2], fortuneList[3], userName, today)
	 * SQL文の動作：おみくじテーブルにおみくじコード、運勢コード、願い事、商い、学問、作成者、作成日を登録
	 *
	 * @param i			setOmikujiAndFortuneTableメソッド内のループカウンタ
	 * @param fortuneId	運勢コード
	 * @param negaigoto	願い事
	 * @param akinai	商い
	 * @param gakumon	学問
	 * @param creater	作成者
	 * @param createDay	作成日
	 * @throws SQLException
	 */
	protected static void insertOmikujiTable(int i, int fortuneId, String negaigoto, String akinai, String gakumon,
			String creater, String createDay) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO omikuji(omikuji_id, fortune_id, negaigoto, akinai, gakumon, creater, create_day) VALUES (");
		sb.append(i);
		sb.append(", ");
		sb.append(fortuneId);
		sb.append(", '");
		sb.append(negaigoto);
		sb.append("', '");
		sb.append(akinai);
		sb.append("', '");
		sb.append(gakumon);
		sb.append("', '");
		sb.append(creater);
		sb.append("', '");
		sb.append(createDay);
		sb.append("')");
		String sql = sb.toString();
		stmt.executeUpdate(sql);
	}

	/**
	 * 結果テーブルに登録する処理(メインクラスで使用)
	 *
	 *SQL文：INSERT INTO result(fortune_day, birthday, omikuji_id, creater, create_day)
	 *			VALUES (today, birthday, omikujiId, userName, today)
	 * SQL文の動作：結果テーブルに占い日付、誕生日、おみくじコード、作成者、作成日を登録する処理
	 * @param tobay		実行日と作成日
	 * @param bday		入力誕生日
	 * @param omikujiId	出力されたおみくじのID
	 * @param creater	作成者
	 * @throws SQLException
	 */
	public static void setResultTable(String today, String birthday, int omikujiId, String creater) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO result(fortune_day, birthday, omikuji_id, creater, create_day) VALUES ('");
		sb.append(today);
		sb.append("', '");
		sb.append(birthday);
		sb.append("', ");
		sb.append(omikujiId);
		sb.append(", '");
		sb.append(creater);
		sb.append("', '");
		sb.append(today);
		sb.append("')");
		String sql = sb.toString();
		stmt.executeUpdate(sql);
		conn.commit();
	}
}
