package com.se22.pojo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

//正式测试
public class CityTest {
    private static City city;

    @BeforeAll
    public static void init(){
        System.out.println("Testing started!");
    }
    @AfterAll
    public static void end(){
        System.out.println("Testing finished!");
    }
    @BeforeEach
    public void setUp() {
        System.out.println("Init");
        city = new City();
    }
    @AfterEach
    public void tearDown() {
        System.out.println("End");
    }

    @Test
    @DisplayName("获取所有城市信息")
    public void getCities() {
//   正常成功获取
        assertNotNull(City.getCities());
//   数据库连接失败
        assertNull(City.getCities());
//   数据库城市表为空
        assertNull(City.getCities());
//   数据库城市表读取失败
        assertNull(City.getCities());
    }

    @Test
    @DisplayName("获取目标城市信息")
    public void getCity() {
// 正常正确获取上海市
        assertNotNull(city.getCity("上海市"));
// 正常正确获取南昌
        assertNotNull(city.getCity("南昌"));
// 异常获取(城市名为空)
        assertNull(city.getCity(""));
// 异常获取(城市名不匹配)
        assertNull(city.getCity("上海"));
    }

    @Test
    @DisplayName("计算城市之间的距离")
    public void getDistance() {

//相同城市且计算结果正确
        assertEquals(0.0,City.getDistance(city.getCity("上海市"),city.getCity("上海市")));
//不同城市且计算结果正确
        assertEquals(667.6192507668463,City.getDistance(city.getCity("上海市"),city.getCity("南昌")));
//存在城市为空
        assertThrows(NullPointerException.class, () -> City.getDistance(city.getCity(""),city.getCity("南昌")));
//不同城市且计算结果正确
        assertEquals(8602.374268624284,City.getDistance(city.getCity("上海市"),city.getCity("巴黎")));

//        System.out.println(city.getDistance(city.getCity("上海市"),city.getCity("巴黎")));
    }

    @Test
    @DisplayName("获取搬家类型")
    public void getMovingType() {
//同城搬家
        assertEquals("同城",City.getMovingType(city.getCity("上海市"),city.getCity("上海市")));
//跨市搬家
        assertEquals("跨市",City.getMovingType(city.getCity("上海市"),city.getCity("南昌")));
//出国搬家
        assertEquals("出国",City.getMovingType(city.getCity("上海市"),city.getCity("巴黎")));
//异常搬家(存在城市为空）
        assertThrows(NullPointerException.class, () -> City.getMovingType(city.getCity(""),city.getCity("巴黎")));

    }

    @Test
    @DisplayName("获取搬家价格")
    public void getPrice() {
//  同城搬家价格
        assertEquals(540.0,City.getPrice("同城",66,5));
//  跨市搬家价格
        assertEquals(10656.0,City.getPrice("跨市",666,4));
//  出国搬家价格
        assertEquals(127987.20000000003,City.getPrice("出国",6666,8));
//  异常搬家(搬家类型异常）
        assertEquals(0,City.getPrice("",666,4));

//        System.out.println(City.getPrice("出国",6666,8));
    }
}