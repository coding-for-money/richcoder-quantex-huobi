richcoder-quantex-huobi

目标： 打造一款火币量化交易平台、数字货币量化交易平台

根据加密货币交易平台huobi对外公开的API开发出的一款量化交易系统，

纯后端代码，不排除以后增加友好的前端代码页面方便查看。

数据库使用Postgresql用于存储部分历史数据，比如k线行情等。

模块说明：

richcoder_huobi_api：对huobi api进行封装，方便使用

richcoder_huobi_common：通用代码

richcoder_huobi_data：火币数据抓取落库 如行情

richcoder_huobi_quantex：量化代码，自定义自己的量化逻辑


安装postsql

```sql

brew install postgresql

brew services start postgresql

psql postgres
#创建用户postgres 略

```


