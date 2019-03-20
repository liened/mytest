package com.exm;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @link https://blog.csdn.net/chenleixing/article/details/44408875
 */
public class LocalDateTest {

    /**
     * Instant——它代表的是时间戳
     * LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
     * LocalTime——它代表的是不含日期的时间
     * LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。
     * ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。
     * 新的库还增加了ZoneOffset及Zoned，可以为时区提供更好的支持。新的DateTimeFormatter
     *
     * TemporalAdjusters - java.time.temporal包：这个包很有意思，封装了一些获取某个特定日期和时间的接口，比如某月的第一天或最后一天，并且这些方法都是属于特别好认的方法。
     */
    @Test
    public void common(){
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        Month month = now.getMonth();
        int dayOfMonth = now.getDayOfMonth();
        int dayOfYear = now.getDayOfYear();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println("year:"+year+",monthVal:"+month.getValue()+",month:"+month+",dayOfmonth:"+dayOfMonth+",dayOfYear:"+dayOfYear+",dayOfWeek:"+dayOfWeek);
        System.out.printf("Year:%d, Month:%d ,day:%d \t %n",year,now.getMonthValue(),dayOfMonth);

        LocalDate myDate = LocalDate.of(2019,3,19);
        System.out.printf("myDate: %s %n",myDate);
        boolean b = now.equals(myDate);
        System.out.println("myDate compare to now:"+b);
        System.out.println("================ Time ===============");
        LocalTime nowTime = LocalTime.now();
        System.out.println("原来的:"+nowTime+" ,不包含毫秒数:"+nowTime.withNano(0));

        LocalTime parseTime = LocalTime.parse("14:59:45");
        System.out.println("parse Time:"+parseTime);

        LocalTime afterePlus = nowTime.plusHours(3);
        System.out.printf("now:%s ,afterPlus:%s %n",nowTime,afterePlus);

        System.out.println("==============LocalDateTime的相互转换==============");
        LocalDateTime n = LocalDateTime.now();
        LocalTime nTime = LocalTime.from(n);
        LocalDate nDate = LocalDate.from(n);
        System.out.println("n:"+n+" ,nDate:"+nDate+" ,nTime:"+nTime);
    }

    @Test
    public void compare(){
        LocalDate now = LocalDate.now();
        LocalDate end = LocalDate.of(2018, 3, 12);
        System.out.println("after:"+now.isAfter(end));
        System.out.println("before:"+now.isBefore(end));
    }

    /**
     * Period和Duration :
     *  Period是基于ISO-8601标准的日期系统，用于计算两个日期间的年，月，日的差值。比如’2年，3个月，4天’；
     *      Period的底层也是LocalDate的until方法,Period操作LocalDate
     *      【注意】:Period的getDays返回的是扣除整月后的天数，是不准确的。
     *
     *  Duration和Period很像，但Duration计算的是两个日期间的秒，纳秒的值，是一种更为精确的计算方式；
     *     Duration操作LocalTime(Duration的between里面没有秒的话会报错)
     *  而ISO-8601系统是当今世界大部分地区采用的现代日历的阳历系统。
     *
     *  总结:计算日期间隔，用ChronoUnit就够了
     */
    @Test
    public void between(){
        LocalDate now = LocalDate.now();
        LocalDate end = LocalDate.of(2019,4,20);
        Period period = Period.between(now,end);
        System.out.println("Period的天数是扣除整月后的天数，是不准确的。天数:"+period.getDays()+",月数:"+period.getMonths());
        long days2 = now.until(end, ChronoUnit.DAYS);
        System.out.println("准确天数一、localDate.until计算天数的until方法:"+days2);

        long days3 = ChronoUnit.DAYS.between(now, end);
        System.out.println("准确天数二、ChronoUnit.DAYS.between:"+days3);

        System.out.println("========== time的比较 ========");
        LocalTime nowTime = LocalTime.now();
        LocalTime endTime = LocalTime.parse("17:30:36");
        long untilSec = nowTime.until(endTime, ChronoUnit.MINUTES);
        System.out.println("比较的时间间隔是:"+untilSec);
    }


    @Test
    public void common2(){
        LocalDate now = LocalDate.now();
        //获取每月第几天的两种方法
        LocalDate firstDay = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate firstDay2 = now.withDayOfMonth(1);
        System.out.println("firstDay:"+firstDay+",firstDay2:"+firstDay2);
        LocalDate dayOfYear3 = now.withDayOfYear(3);
        System.out.println("本年第3天:"+dayOfYear3);

        LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfYear());
        System.out.println(lastDay);
    }

    @Test
    public void format(){
        String date = "20180319";
        //LocalDate.parse(string,formatter);formatter默认是yyyy-MM-dd,所以默认格式时可以写成:LocalDate.format("2019-03-19");
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("localDate:"+localDate);

        String date2 = "2019/03/20";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate2 = LocalDate.parse(date2, formatter);
        System.out.println("localDate2:"+localDate2);

        System.out.println("============将LocalDate转成指定的格式==========");
        String format = localDate.format(formatter);
        String format2 = formatter.format(localDate);

        System.out.println("两种方法,底层方法一样,转成指定格式后:"+format+" ,format2:"+format2);
    }

    /**
     * 当前时间戳
     *  可与Date相互转换
     */
    @Test
    public void timestamp(){
        Instant instant = Instant.now();
        System.out.println(instant);
        java.util.Date date = Date.from(instant);
        System.out.println(date);

        Instant instant2 = Clock.system(ZoneId.of("Asia/Shanghai")).instant();
        System.out.println("instant2:"+instant2);
    }

    @Test
    public void specailDay(){
        MonthDay monthDay = MonthDay.of(3,19);
        MonthDay nowMonthDay = MonthDay.now();
        if (monthDay.equals(nowMonthDay)){
            System.out.println("是约定的日子");
        }else {
            System.out.println("不是约定的日子,NO!");
        }
        System.out.println(monthDay);
        System.out.println("===============");
        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        YearMonth plus = yearMonth.plus(1, ChronoUnit.MONTHS);
        System.out.println(plus);

        YearMonth myYearMonth = YearMonth.of(2018,Month.FEBRUARY);
        System.out.println("myYearMonth:"+myYearMonth+",monthLength:"+myYearMonth.lengthOfMonth());
        System.out.println("===============判断是否是闰年=========");
        boolean leapYear = LocalDate.now().isLeapYear();
        System.out.println("LocalDate才有的方法，判断是否是闰年:"+leapYear);
    }

    @Test
    public void add(){
        LocalDate now = LocalDate.now();
        LocalDate nextWeek = now.plus(1, ChronoUnit.WEEKS);
        System.out.println("today is:"+now);
        System.out.println("Date after a week:"+nextWeek);
        LocalDate plus7 = now.plusDays(7);
        System.out.println("plus 7 is:"+plus7);

        LocalDate lastYear = now.minus(1, ChronoUnit.YEARS);
        System.out.println("today is:"+now);
        System.out.println("last year is:"+lastYear);

        LocalDate nextYear = now.plus(1, ChronoUnit.YEARS);
        System.out.println("next year is:"+nextWeek);
    }

    /**
     * Java 8中自带了一个Clock类，你可以用它来获取某个时区下当前的瞬时时间，日期或者时间。可以用Clock来替代System.currentTimeInMillis()与 TimeZone.getDefault()方法。
     */
    @Test
    public void clock(){
        Clock clock = Clock.systemUTC();
        System.out.println("clock is:"+clock+",millis:"+clock.millis()+",system:"+System.currentTimeMillis());
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("default zone clock"+defaultClock+",millis:"+defaultClock.millis()+",system:"+System.currentTimeMillis());

        LocalDateTime clockDateTime = LocalDateTime.now(clock);
        LocalDateTime defaultClockDateTime = LocalDateTime.now(defaultClock);
        System.out.println("clockDateTime:"+clockDateTime+",defaultClockDate:"+defaultClockDateTime);

        LocalDate eventDate = LocalDate.of(2019, 3, 19);
        if (eventDate.isBefore(LocalDate.now(defaultClock))){
            System.out.println("eventDate is before default Clock");
        }
        if (eventDate.isBefore(LocalDate.now(clock))){
            System.out.println("eventDate is before clock");
        }

        //芝加哥
        Clock cst = Clock.system(ZoneId.of(ZoneId.SHORT_IDS.get("CST")));
        System.out.println("cst:"+LocalDateTime.now(cst));
        System.out.println("-- end --");
    }

    @Test
    public void zone(){
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(now, zoneId);
        System.out.println("now:"+now+",zoneDateTime:"+zonedDateTime);
    }

    /**
     * 与老日期类型的转换
     */
    @Test
    public void swapToLowVersion(){
        Date date = new Date();
        System.out.println("date :"+date);
        //date -> localDateTime
        LocalDateTime localDateTime1 = date.toInstant().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime();
        System.out.println("第一种方法、localDateTime1:"+localDateTime1);

        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("Asia/Shanghai"));
        System.out.println("第二种方法、localDateTime2:"+localDateTime2);

        //date -> localDate
        LocalDate localDate = date.toInstant().atZone(ZoneId.of("Asia/Shanghai")).toLocalDate();
        System.out.println("localDate:"+localDate);

        //localDateTime向下兼容
        LocalDateTime localDateTime = LocalDateTime.now();
        Date formatDate = Date.from(localDateTime.atZone(ZoneId.of("Asia/Shangnhai")).toInstant());

        LocalDate toDateLocalDate = LocalDate.now();
        Date.from(toDateLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }









}
