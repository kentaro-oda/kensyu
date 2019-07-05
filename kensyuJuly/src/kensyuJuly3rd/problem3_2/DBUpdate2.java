package kensyuJuly3rd.problem3_2;

import java.sql.Date;
import java.sql.SQLException;

import kensyuJuly3rd.problem3.DBUpdate;

/**
 * 更新処理を集めたクラス(UPDATEタイプのSQL文)
 * @author k_oda
 *
 */

public class DBUpdate2 extends DBUpdate{

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
	protected static void updateFortuneId(int fortuneId, String updater, Date today, int i) throws SQLException {
		String sql = "UPDATE omikuji SET fortune_id = ?, updater = ?, update_day = ? WHERE omikuji_id = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, fortuneId);
		ps.setString(2, updater);
		ps.setDate(3, today);
		ps.setInt(4, i);
		ps.executeUpdate();
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
	protected static void updateNegaigoto(String negaigoto, String updater, Date today, int i) throws SQLException {
		String sql = "UPDATE omikuji SET negaigoto = ?, updater = ?, update_day = ? WHERE omikuji_id = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, negaigoto);
		ps.setString(2, updater);
		ps.setDate(3, today);
		ps.setInt(4, i);
		ps.executeUpdate();
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
	protected static void updateAkinai(String akinai, String updater, Date today, int i) throws SQLException {
		String sql = "UPDATE omikuji SET akinai = ?, updater = ?, update_day = ? WHERE omikuji_id = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, akinai);
		ps.setString(2, updater);
		ps.setDate(3, today);
		ps.setInt(4, i);
		ps.executeUpdate();
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
	protected static void updateGakumon(String gakumon, String updater, Date today, int i) throws SQLException {
		String sql = "UPDATE omikuji SET gakumon = ?, updater = ?, update_day = ? WHERE omikuji_id = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, gakumon);
		ps.setString(2, updater);
		ps.setDate(3, today);
		ps.setInt(4, i);
		ps.executeUpdate();
	}
}
