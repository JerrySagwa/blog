# 项目注意点

> 视频来自哔哩哔哩码神之路

## 子查询应用：tag 表和 tag_article 表

```sql
select <include refid="all" />  from ms_tag
<where>
id in
(select tag_id from ms_article_tag where article_id = #{articleId})
</where>
```



## 查询热点文章的 SQL

```sql
select tag_id 
from ms_article_tag
group by tag_id
order by count(1) desc
limit #{limit}
```



## 统一异常处理 -- 对用户友好

```java
// 对加了 @Controller 注解的方法进行拦截 AOP
@ControllerAdvice
public class AllExceptionHandler {

    // 处理 Exception.class 异常
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回 JSON 数据
    public Result doException(Exception e) {
        e.printStackTrace();
        return Result.fail(-999, "系统异常");
    }

}

```

```json
{
    "success": false,
    "code": -999,
    "msg": "系统异常",
    "data": null
}
```



## 登录

