# TD-SPB-SPC-MAIN

#### 介绍
TD-SPB-SPC-MAIN开源后台管理系统由java开发基于SpringBoot2.x， springcloud G的全新版本，架构清晰、代码整洁、页面美观，
可用于OA系统、 CRM系统、 PDM系统等二次开发，非常适用于公司管理平台旧版本升级、新平台搭建快速整合。
同时提供API接口服务（供APP、微端、h5等使用）。
API接口服务概述：
1. API接口服务与smp管理平台完全分离，只依赖通用dbean模块和统一父类。 
2. 服务提供者负责DB交互通用dbean模块。
3. 服务消费者负对外开放包括APP、微端、h5等，不负责业务逻辑处理（后期增加【验签】保证API接口服务的安全性）。
4. API接口服务使用Eureka为注册中心，消费者负载使用Feign并使用Hystrix熔断器。

#### 软件架构
通用模块架构：
- td-parent模块：所有项目父类，负责jar的依赖和版本管理。
- td-commons-dbean模块：通用db和bean。
- td-config-servser：cloud配置中心服务端。

SMP管理平台架构
- td-smp-management模块：管理平台，主要为controller层和视图文件。
- td-smp-generator模块：代码生成器。
- td-smp-quartz模块：定时任务。
- td-smp-core模块：核心模块，包过权限处理、持久化操作、工具类、配置中心客户端、数据源等。

API接口服务架构：
- td-application-eureka：Eureka注册中心模块。
- td-api-provider：服务提供者模块。
- td-api-consumer：服务消费者模块。

 **后台框架介绍：** 
|  序号  |  核心技术  |  框架   | 阐述 |
| --- | --- | --- | --- |
|1|核心框架|Spring Boot2.x，springcloud G版本|springBoot为核心框架，springcloud为辅核心框架（API接口服务，config配置中心）|
|2|安全框架|Apache Shiro|core模块|OK
|3|模板引擎|Thymeleaf|smp模块|
|4|持久层框架|MyBatis|实现：dbean模块 配置：core模块|
|5|定时任务|Quartz|quartz模块|
|6|数据库连接池|Druid|实现：dbean模块 配置：core模块|
|7|代码生成|Velocity|generator模块    |
|8|项目管理|Maven|缺省  |
|9|缓存技术|Shiro自带缓存| （配置：core模块），core模块   |
|10|注册中心|Eureka|注册中心application-eureka模块   |
|11|负载均衡|Feign|服务消费者consumer模块 |
|12|熔断机制|Hystrix|服务提供者provider模块 |
|13|其他插件|tk.mybatis，lombok等|（dben模块，core模块），全局  |

 **前端框架：**
|  序号  |  核心技术  |  框架   | 阐述 |
| --- | --- | --- | --- |
|1|核心框架|JQuery、Bootstrap|缺省|
|2|table表格|bootstrap-table|缺省|
|3|表格树插件|bootstrap-treetable|缺省|
|4|表单导出|bootstrap-table-export、tableExport|缺省|
|5|遮罩层弹出框|layui、layer|缺省|
|6|ztree树插件|jquery.ztree|缺省|
|7|下拉框插件|select2.min、bootstrap-select|缺省|
|8|时间插件|bootstrap-datetimepicker|缺省|
|9|富文本编辑|summernote|缺省|
|10|文件上传|bootstrap-fileinput|缺省|
|11|统计报表|echarts-all、jquery.peity、jquery.sparkline|缺省|
|12|表单验证|jquery.validate|缺省|

#### 下个版本：
1.  增加API接口服务【验签】保证API接口服务的安全性。
2.  增加smp管理平台sso实现。

#### 原版TD-SPB-MAIN升级调整介绍

1.  **取消项目聚合：** 本人多接触快速搭建管理平台的需求，评估后感觉聚合项目不太适合，所以改使用普通父类子类集成，有新项目时直接继承统一父类，保障快速开发，版本统一。 
2.  **模块调整：** 整合system持久化模块，为通用的td-commons-dbean可以供其他服务（如：微端服务、APP接口服务）使用。整合common通用模块，framework核心模块为td-smp-core核心模块，对于中小项目来说，原项目模块太繁琐，各个模块分工不明确，整合为一个模块当有新项目需求事可以快速搭建。
3. **项目调整：** java代码神器lombok、消息转换器HttpMessageConverter...
4. **框架管理：** 增加tk.mybatis插件，原项目虽然可以用代码生成器直接生成增删改查语句，但是繁琐业务下，需要在xml写sql映射过于繁琐，使用tk.mybatis插件可以直接使用封装快速方法，极高的保障了开发的效率。
5.  **配置管理：** 增加cloud config配置中心，当项目生态系统不仅限于管理平台，繁琐的配置成为增加工作量和出现问题的关键，所以增加配置中心，统一管理配置文件。
6.  **增加模块：** config-servser配置中心模块（可选），API接口服务模块（独立）。 
7.  **API接口服务：** 2.0版本不在局限于单一的后台管理框架，重点打造TD-SPB-SPC-MAIN管理平台生态圈，增加API接口服务（供APP、微端、h5等使用），该服务以spring cloud为核心，使用Eureka为注册中心、Feign负载、Hystrix熔断器。

#### 项目部署要求
- JDK-v1.8
- MySql-v5.7.x（建议）
- Maven-v3.3.x（建议）
- SVN服务器（可选）如果使用配置中心则需要此项，反之 **需要调整代码不通过springCloud config获取配置信息** ，或者使用git。
- GIT服务器（可选）如果使用配置中心则需要此项，反之 **需要调整代码不通过springCloud config获取配置信息** ，或者使用svn。

#### 项目运行须知
1. 项目运行有两种模式，详细见项目运行步骤。 

#### SMP项目运行步骤一： **使用配置中心** 
1. 创建数据库，执行数据库脚本;导入项目，导入完成后需要确保是maven项目，如果不是需要转换成maven项目。
2. 发布项目到maven仓库，依次为td-smp-parent->td-commons-dbean->td-smp-core/generator/quartz 。
3. 启动td-smp-config模块，该模块为springCloud config-server端 ，需要svn服务器或git服务器把配置中心文件放到对应svn/git上，并且需要配置bootstrap.properties文件。
4. 启动td-smp-management，如果使用springCloud config需要配置bootstrap.properties文件。

#### SMP项目运行步骤二： **不使用配置中心** 
1. 创建数据库，执行数据库脚本;导入项目，导入完成后需要确保是maven项目，如果不是需要转换成maven项目。
2. 发布项目到maven仓库，依次为td-smp-parent->td-commons-dbean->td-smp-core/generator/quartz。
4. 把配置中心文件放入项目resources目录下，启动td-smp-management。

#### API项目运行步骤：
1. 启动td-smp-config配置中心模块。
2. 启动td-application-eureka注册中心模块。
3. 启动td-api-provider服务提供者模块。
4. 启动td-api-consumer服务消费者模块。
注：参考SMP项目运行步骤

#### 演示地址
- 地址：http://localhost:7122/tdd
- 账号：tutu
- 密码：123456