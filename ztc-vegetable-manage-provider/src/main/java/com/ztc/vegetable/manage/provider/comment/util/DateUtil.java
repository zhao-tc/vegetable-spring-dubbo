package com.ztc.vegetable.manage.provider.comment.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 * @date: 2018年9月15日-下午3:52:39
 * @version:
 */
public class DateUtil {

    private static Map<String, SimpleDateFormat> dateFmtMap = new HashMap<String, SimpleDateFormat>();
    private static final String LOCK_OBJ = "LOCK";

    public static final String DATE_TYPE_Y = "Y";
    public static final String DATE_TYPE_A = "A";
    public static final String DATE_TYPE_M = "M";
    public static final String DATE_TYPE_D = "D";
    public static final String DATE_TYPE_W = "W";
    public static final String DATE_TYPE_H = "H";
    public static final String DATE_TYPE_MI = "MI";

    /**
     * 获取系统当前时间
     *
     * @return
     * @history: 2016年1月3日
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取系统当前时间字符串
     *
     * @param pattern
     * @return
     */
    public static String getNowDateStr(String pattern) {
        Date currDate = new Date();
        if (StringUtils.isBlank(pattern)) {
            return null;
        }
        return getDateStr(currDate, pattern);
    }

    /**
     * 日期字符串格式
     * <p>
     * 2016年1月17日
     *
     * @param sourceDate
     * @param format
     * @return
     */
    public static synchronized String getDateStr(Date sourceDate, String format) {
        if (null == sourceDate) {
            return "";
        }
        SimpleDateFormat df = getSimpleDateFormat(format);
        return df.format(sourceDate);
    }

    private static SimpleDateFormat getSimpleDateFormat(String format) {
        SimpleDateFormat sdf = null;
        if (dateFmtMap.containsKey(format)) {
            sdf = dateFmtMap.get(format);
            return sdf;
        }
        synchronized (LOCK_OBJ) {
            sdf = new SimpleDateFormat(format, Locale.ENGLISH);
            dateFmtMap.put(format, sdf);
        }
        return sdf;
    }

    /**
     * 通过起始日期和终止日期计算以时间间隔单位为计量标准的时间间隔 author: HST
     * <p>
     * <b>Example: </b>
     * <p>
     * <p>
     * 参照calInterval(String cstartDate, String cendDate, String unit)，前两个变量改为日期型即可
     * <p>
     *
     * @param startDate 起始日期，Date变量
     * @param endDate   终止日期，Date变量
     * @param unit      时间间隔单位，可用值("Y"--年 "M"--月 "D"--日)
     * @return 时间间隔, 整形变量int
     */
    public static int calInterval(Date startDate, Date endDate, String unit) {
        int interval = 0;

        GregorianCalendar sCalendar = new GregorianCalendar();
        sCalendar.setTime(startDate);
        int sYears = sCalendar.get(Calendar.YEAR);
        int sMonths = sCalendar.get(Calendar.MONTH);
        int sDays = sCalendar.get(Calendar.DAY_OF_MONTH);
        int sDaysOfYear = sCalendar.get(Calendar.DAY_OF_YEAR);

        GregorianCalendar eCalendar = new GregorianCalendar();
        eCalendar.setTime(endDate);
        int eYears = eCalendar.get(Calendar.YEAR);
        int eMonths = eCalendar.get(Calendar.MONTH);
        int eDays = eCalendar.get(Calendar.DAY_OF_MONTH);
        int eDaysOfYear = eCalendar.get(Calendar.DAY_OF_YEAR);

        if (DATE_TYPE_Y.equals(unit)) {
            interval = eYears - sYears;
            if (eMonths < sMonths) {
                interval--;
            } else {
                if (eMonths == sMonths && eDays < sDays) {
                    interval--;
                    if (eMonths == 1) {
                        // 如果同是2月，校验润年问题
                        if ((sYears % 4) == 0 && (eYears % 4) != 0) {
                            // 如果起始年是润年，终止年不是润年
                            if (eDays == 28) {
                                // 如果终止年不是润年，且2月的最后一天28日，那么补一
                                interval++;
                            }
                        }
                    }
                }
            }
        }
        if (DATE_TYPE_M.equals(unit)) {
            interval = eYears - sYears;
            interval *= 12;
            interval += eMonths - sMonths;
            if (eDays < sDays) {
                interval--;
                // eDays如果是月末，则认为是满一个月
                int maxDate = eCalendar.getActualMaximum(Calendar.DATE);
                if (eDays == maxDate) {
                    interval++;
                }
            }
        }
        if (DATE_TYPE_D.equals(unit)) {
            interval = eYears - sYears;
            interval *= 365;
            interval += eDaysOfYear - sDaysOfYear;

            // 处理润年
            int n = 0;
            eYears--;
            if (eYears > sYears) {
                int i = sYears % 4;
                if (i == 0) {
                    sYears++;
                    n++;
                }
                int j = (eYears) % 4;
                if (j == 0) {
                    eYears--;
                    n++;
                }
                n += (eYears - sYears) / 4;
            }
            if (eYears == sYears) {
                int i = sYears % 4;
                if (i == 0) {
                    n++;
                }
            }
            interval += n;
        }
        return interval;
    }

    /**
     * 加减天
     *
     * @param sourceDate
     * @param dayIntval
     * @return
     */
    public static synchronized Date calDate(Date sourceDate, int dayIntval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.add(Calendar.DATE, dayIntval);
        return calendar.getTime();
    }

    /**
     * 加减分钟
     *
     * @param sourceDate
     * @param minuteIntval
     * @return
     */
    public static synchronized Date calMinute(Date sourceDate, int minuteIntval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.add(Calendar.MINUTE, minuteIntval);
        return calendar.getTime();
    }

    /**
     * 加减秒
     *
     * @param sourceDate
     * @param secondIntval
     * @return
     */
    public static synchronized Date calSecond(Date sourceDate, int secondIntval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.add(Calendar.SECOND, secondIntval);
        return calendar.getTime();
    }

    /**
     * 加天数
     *
     * @param date
     * @param amount
     * @return
     * @history: 2019年1月18日
     * @author: wuyc_sinosoft
     */
    public static Date addDays(Date date, int amount) {
        return DateUtils.addDays(date, amount);
    }

    /**
     * 日期计算，月加减
     *
     * @param date   初始日期
     * @param amount 月数增量（负数为减）
     * @return 计算后的日期
     */
    public static Date addMonths(Date date, int amount) {
        return DateUtils.addMonths(date, amount);
    }

    /**
     * 日期计算，年加减
     *
     * @param date   初始日期
     * @param amount 年数增量（负数为减）
     * @return 计算后的日期
     */
    public static Date addYears(Date date, int amount) {
        return DateUtils.addYears(date, amount);
    }

    /**
     * 清除时分秒，只保留年月日
     *
     * @param date
     * @return
     * @author fengxiao
     * @date 2019年1月22日
     */
    public static Date cleanTimes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 将时分秒,毫秒域清零
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当前小时数
     *
     * @Class DateUtil
     * @author WangTianZhou
     * @date 2020/1/3
     */
    public static int getNowHours() {
        Calendar cal = Calendar.getInstance();
        // 将时分秒,毫秒域设置为需要的数值
        return cal.get(Calendar.HOUR_OF_DAY);
    }


}
