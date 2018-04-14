package common;

/**
 * 打印日志
 */
public class Log {
	private static Boolean debug = true;

	public static void i(String TAG, String str) {
		if (debug == true) {
			StackTraceElement[] stes = Thread.currentThread().getStackTrace();
			System.out.println(TAG == null ? "" : TAG );
			for (int i = 0; i < 3; i++) {
				System.out.print( ">>" + stes[i].getClassName() + "|" + stes[i].getMethodName() + "|" + stes[i].getFileName() + "|  \n");
			}
			System.out.println(">>>msg:" + str);
		}
	}
}
