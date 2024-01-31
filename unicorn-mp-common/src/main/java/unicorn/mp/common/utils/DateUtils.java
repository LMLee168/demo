package unicorn.mp.common.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = 60 * ONE_SECOND;
    public static final long ONE_HOUR = 60 * ONE_MINUTE;
    public static final long ONE_DAY = 24 * ONE_HOUR;
    public static final long ONE_MONTH = 30 * ONE_DAY;
    public static final long ONE_YEAR = 12 * ONE_MONTH;

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_BAR = "yyyy/MM/dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String MMDD = "MMdd";
    public static final String YYYY_MM_DD_HH_MM_SS_CON = "yyyy-MM-dd-HH-mm-ss";
    public static final String YYYY_MM_DD_HH_MM_CON = "yyyy-MM-dd-HH-mm";

    public static final String CRON_STR = "ss mm HH dd MM ? yyy";

    public static String formatDateTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDateTime);
    }

    public static String formatDateTime(long timestamp, String pattern) {
        LocalDateTime localDateTime = parseFromTimestamp(timestamp);
        return formatDateTime(localDateTime, pattern);
    }

    public static LocalDateTime parseFromTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static LocalDateTime parseFromString(String timeStr, String formatter) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(formatter);
        return LocalDateTime.parse(timeStr, df);
    }

    public static long getTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    public static long LocalDateToTimestamp(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDate longToLocalDate(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.ofHours(8)).toLocalDate();
    }

    public static LocalDate date2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date parseDateFromTimeString(String timeStr, String formatter) {
        SimpleDateFormat fmt = new SimpleDateFormat(formatter);
        Date date = null;
        try {
            date = fmt.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }


    /**
     * 获取当天开始时间
     *
     * @param date
     * @return
     */
    public static LocalDateTime getStartOfDay(LocalDateTime date) {
        return LocalDateTime.of(date.toLocalDate(), LocalTime.MIN);
    }

    /**
     * 获取当天结束时间
     *
     * @param date
     * @return
     */
    public static LocalDateTime getEndOfDay(LocalDateTime date) {
        return LocalDateTime.of(date.toLocalDate(), LocalTime.MAX);
    }

    /**
     * 获取当天开始时间
     *
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当天结束时间
     *
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date afterDay(Date date, Integer offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, offset);
        return cal.getTime();
    }

    public static Date afterHour(Date date, Integer offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, offset);
        return cal.getTime();
    }

    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static int age(LocalDateTime birthday) {
        return yearDateDiff(birthday, LocalDateTime.now());
    }

    //计算两个日期相差年数
    public static int yearDateDiff(LocalDateTime startDate, LocalDateTime endDate) {
        // 先截取到字符串中的年、月、日
        int selectYear = startDate.getYear();
        int selectMonth = startDate.getMonth().getValue();
        int selectDay = startDate.getDayOfMonth();
        // 得到当前时间的年、月、日
        int yearNow = endDate.getYear();
        int monthNow = endDate.getMonth().getValue();
        int dayNow = endDate.getDayOfMonth();

        // 用当前年月日减去生日年月日
        int yearMinus = yearNow - selectYear;
        int monthMinus = monthNow - selectMonth;
        int dayMinus = dayNow - selectDay;

        int age = yearMinus;// 先大致赋值
        if (yearMinus < 0) {// 选了未来的年份
            age = 1;
        } else if (yearMinus == 0) {// 同年的，要么为1，要么为0
            age = 1;
        } else {
            if (monthMinus == 0) {
                if (dayMinus >= 0) {
                    age += 1;
                }
            } else if (monthMinus > 0) {
                age += 1;
            }
        }
        return age - 1;
    }

    //相差天数
    public static int daysDiff(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) Math.abs((getTimestamp(startDate) - getTimestamp(endDate)) / ONE_DAY);
    }

    public static int minutesDiff(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) Math.abs((getTimestamp(startDate) - getTimestamp(endDate)) / ONE_MINUTE);
    }

    public static int daysDiffWithoutAbs(LocalDateTime startDate, LocalDateTime endDate) {
        return (int) ((getTimestamp(startDate) - getTimestamp(endDate)) / ONE_DAY);
    }

    // 判断某个时间是否在时间区间内
    public static boolean isBetweenTimes(Date time, Date startTime, Date endTime) {
        if (time.after(startTime) && time.before(endTime)) {
            return true;
        }
        return false;
    }

    // 当天剩余时间
    public static long remainTime() {
        LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        long millSeconds = ChronoUnit.MILLIS.between(LocalDateTime.now(), midnight);
        return millSeconds;
    }

    // 今日几点时间戳
    public static long millisOfTodayHour(int hour) {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 距离某时间多久的时间
     * 比如 距离当前时间的第二天9点
     * rangeTodayHour(new Date(), 1, 9)
     *
     * @param date
     * @param range
     * @param hour
     * @return
     */
    public static Date rangeDateHour(Date date, int range, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, range);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getCurMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static int getCurDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    public static LocalDateTime getCurMinuteTime() {
        LocalTime localTime = LocalTime.now();
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(localTime.getHour(), localTime.getMinute(), 0));
    }

    public static List<DateRange> partitionDate(LocalDateTime startTime, LocalDateTime endTime, int size) {
        Preconditions.checkNotNull(startTime);
        Preconditions.checkNotNull(endTime);
        Preconditions.checkArgument(size > 0);
        Preconditions.checkArgument(startTime.isBefore(endTime));
        long start = getTimestamp(startTime) / 1000;
        long end = getTimestamp(endTime) / 1000;
        if (end - start <= size || size == 1) {
            return Lists.newArrayList(new DateRange(startTime, endTime));
        }
        ArrayList<DateRange> dateRanges = new ArrayList<>(size);
        long l = (end - start) / size;
        long l1 = (end - start) % size;
        for (int i = 0; i < size; i++) {
            dateRanges.add(new DateRange(startTime.plusSeconds(i * l), startTime.plusSeconds((i + 1) * l)));
            if (i == size - 1){
                dateRanges.get(i).getEndTime().plusSeconds(l1);
            }
        }
        return dateRanges;
    }

    /**
     * long格式毫秒的时间差转为x天x小时x分x秒
     * @param dateDiff
     * @return
     */
    public static String praseFromLong2DHMSString(Long dateDiff) {
        StringBuffer sb = new StringBuffer();

        long day = dateDiff / ONE_DAY;
        if (day > 0) {
            String strDay = day + "天";
            sb.append(strDay);
            dateDiff = dateDiff - day * ONE_DAY;
        }
        long hour = dateDiff / ONE_HOUR;
        if (hour > 0) {
            String strHour = hour + "时";
            sb.append(strHour);
            dateDiff = dateDiff - hour * ONE_HOUR;
        } else if (day > 0 && hour==0) {
            String strHour = hour + "时";
            sb.append(strHour);
            dateDiff = dateDiff - hour * ONE_HOUR;
        }
        long minute = dateDiff / ONE_MINUTE;
        if (minute>0) {
            String strMinute = minute + "分";
            sb.append(strMinute);
            dateDiff = dateDiff - minute * ONE_MINUTE;
        } else if (hour >0 && minute ==0 ) {
            String strMinute = minute + "分";
            sb.append(strMinute);
            dateDiff = dateDiff - minute * ONE_MINUTE;
        }
//        String second = dateDiff / 1000 + "秒";
//        sb.append(second);
        return sb.toString();
    }

    public static void main(String args[]) {
//        LocalDateTime now = LocalDateTime.now().minusDays(30).minusMinutes(24);
//        System.out.println(now);
//        List<DateRange> partition = partitionDate(now,LocalDateTime.now() , 3);
//        System.out.println(LocalDateTime.now());
//        System.out.println(JSONObject.toJSONString(partition));

        System.out.println(praseFromLong2DHMSString(111084313L));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DateRange {
        private LocalDateTime startTime;
        private LocalDateTime endTime;
    }
}
