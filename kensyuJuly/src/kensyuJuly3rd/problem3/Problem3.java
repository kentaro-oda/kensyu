package kensyuJuly3rd.problem3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * おみくじを引くメインクラス
 *
 * @author k_oda
 *
 */
public class Problem3 {

	public static void main(String[] args) {

		try {
			/**
			 * DBに接続
			 */
			DBRelation.getConnection();

			/**
			 * 名前の入力(作成者または更新者記載時に使用するため)
			 */
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(Template.INPUT_USER_NAME);
			String userName = br.readLine();

			/**
			 * 実行日の情報を取得(作成日または更新日記載時に使用するため)
			 *
			 * now 実行時の日時情報
			 * sdf 年月日を8桁の整数型に表示させるフォーマット
			 * today 実行日の年月日をフォーマットに従い格納する変数
			 */
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sdf.setLenient(false);
			String today = sdf.format(now);

			/**
			 * 運勢とおみくじのテーブルにデータを登録・更新
			 */
			DBRelation.setOmikujiAndFortuneTable(userName, today);

			/**
			 * 誕生日入力処理
			 *
			 * birthday 入力された誕生日を格納するための変数
			 */
			System.out.println(Template.BIRTHDAY_INPUT);
			String birthday;

			/**
			 * 誕生日の入力チェック
			 *
			 * do-while文 入力チェックによりbirthdayに値が角野されるまでのループ
			 * if文 入力された誕生日が暦上正しくない場合、birthdayをnullにして再入力を促す処理
			 */
			do {
				birthday = br.readLine();

				if (!DateCheck.isBirthday(birthday)) {
					birthday = null;
					System.out.println(Template.INPUT_ERROR);
					System.out.println(Template.REINPUT);
				}
			} while (birthday == null);

			/**
			 * 過去に同日、同誕生日でおみくじを引いていた場合のおみくじコードを取得(ない場合はnullが入る)
			 */
			Integer f = DBSelect.getPastOmikuji(today, birthday);

			/**
			 * 乱数生成用の引数作成
			 *
			 * r 乱数生成用の変数(引数は実行日と入力した誕生日を文字列結合させlong型に変換したもの)
			 */
			if(f == null) {
			Random r =  new Random();
			int n = DBSelect.getOmikujiCount();
			f = r.nextInt(n);
			}

			/**
			 * 結果をコンソールに出力
			 *
			 * omikuji 上記で取得したfをインデックスとしてリストから情報を取得
			 */
			Omikuji omikuji = DBSelect.getOmikuji(f);
			System.out.println(omikuji.disp());

			/**
			 * 新規の情報だった場合、結果テーブルに情報を登録
			 */
			if(DBSelect.getPastOmikuji(today, birthday) == null) {
				DBInsert.setResultTable(today, birthday, f, userName);
			}


		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			DBRelation.closeConnection();
		}
	}

}
