package kensyuJuly3rd.problem3_2;

import java.sql.Date;
import java.sql.SQLException;

import kensyuJuly3rd.problem3.DBInsert;

/**
 * 登録処理を集めたクラス(INSERTタイプのSQL文)
 * @author k_oda
 *
 */
public class DBInsert2 extends DBInsert {

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
			String creater, Date createDay) throws SQLException {
		String sql = "INSERT INTO omikuji(omikuji_id, fortune_id, negaigoto, akinai, gakumon, creater, create_day) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, i);
		ps.setInt(2, fortuneId);
		ps.setString(3, negaigoto);
		ps.setString(4, akinai);
		ps.setString(5, gakumon);
		ps.setString(6, creater);
		ps.setDate(7, createDay);
		ps.executeUpdate();
	}

	/**
	 * 結果テーブルに登録する処理(メインクラスで使用)
	 *
	 *SQL文：INSERT INTO result(fortune_day, birthday, omikuji_id, creater, create_day)
	 *			VALUES (today, birthday, omikujiId, userName, today)
	 * SQL文の動作：結果テーブルに占い日付、誕生日、おみくじコードを登録する処理
	 * @param tobay		実行日と作成日
	 * @param bday		入力誕生日
	 * @param omikujiId	出力されたおみくじのID
	 * @param creater	作成者
	 * @throws SQLException
	 */
	public static void setResultTable(Date today, Date birthday, int omikujiId, String creater) throws SQLException {
		String sql = "INSERT INTO result(fortune_day, birthday, omikuji_id, creater, create_day) VALUES (?, ?, ?, ?, ?)";
		ps = conn.prepareStatement(sql);
		ps.setDate(1, today);
		ps.setDate(2, birthday);
		ps.setInt(3, omikujiId);
		ps.setString(4, creater);
		ps.setDate(5, today);
		ps.executeUpdate();
		conn.commit();
	}
}
