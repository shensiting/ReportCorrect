==================================================================  
1静态资源的引用
==================================================================
---类型------------存放路径----------------------访问路径----------------
==================================================================
images        /WEB-INF/res/images/		  <%=basePath %>images/**
css           /WEB-INF/res/css/			  <%=basePath %>css/**
js            /WEB-INF/res/js/			  <%=basePath %>js/**
==================================================================
2关于GeneratorUtil工具使用自动生成model，mapper，mapper.xml
==================================================================
1,修改generatorConfiguration.xml中要生成那张表和生成实体类名
2,以java application运行GeneratorUtil,刷新项目即可看到  
3,将生成的Mapper继承BaseMapper,泛型选择刚生成的实体类，将Mapper中的所有方法删除
4,修改实体类的id的类型为int 数据库对应的是Integer
5,将mapper.xml中的 java.lang.Integer替换成int ，",jdbcType="VARCHAR""，",jdbcType="INTEGER""  换成空格 ，（使用ctrl+f替换全部）
6,将mapper.xml中Base_Column_List的内容替换掉 <include refid="Base_Column_List" />的内容，这是mybatis的一个BUG
7,将BaseMapper中的方法名对应mapper.xml方法名,去掉insertSelective,新增getAll的实现。
==================================================================
3关于一些安全措施
==================================================================
1,统一处理错误异常。
2,没有登录不能访问系统除login.jsp之外的其他地址。
3,记住用户登录账号密码，3天之内不需要重新登录直接转到首页。
4,一个账户不能同时登录（选）。

==================================================================

 ==================================================================
5.代码提交要求
==================================================================
 1，提交前先 update一下，切记！
 2，遇到文件冲突先要解决文件冲突的问题，没有解决不能提交！
 3，提交代码需要填写提交备注。  
 
 jacob配置http://www.iteye.com/topic/588050 所需dll放在cpts_161_bet文件夹,使用word转换
 score表格中的charsum是否需要，pdf中的grade是结论成绩？
 labprocess不需要
 生成pdf和二维码里面的路径配置时要重置
 
 ==================================================================
 
 实验报告管理系统细节
1.批改按钮批改之后从浏览器返回再次点击同个按钮时会出现错误提示，提示用户该文档已经被批改。
2.老师不能看到自己不能批改的文档，但是可以看到成绩。
3.老师注册需要验证。
4.当这份文档被其他老师先点击批改，在另外一个老师点击批改时会出现提示，提示该文档已经被批改。
5.可以 根据前台传来的checkBoxId进行表格导出，可以进行班级选择导出表格
6.当教师批改后学生不能再上传，教师批改后把实验改为提交状态时的应为：报告批改完毕
7.采用MD5进行一次加密，salt写在md5.js文件内（重新布置时要修改密码，为用户的密码设置MD5加密）
8.当教师批改文档为及格时，后台自动生成证书，再次将实验设置为提交状态时，及格的学生不能再提交文档。
当不及格时，自动加入补考名单，
当将实验设置为提交状态时，补考学生可以提交实验，可以多次补考，直到及格。
9.报告提交失败或者教师批改文档失败时重新加载不会丢失之前填写的数据
10.退出登录时销毁session，当用户利用浏览器便利返回上一个页面时，一旦点击任意链接，
 使用过滤器强制用户返回登录页面重新登录。
11.身份证号加密
12.使用Log4j监测，当网站产生错误信息或者警告信息存入错误和警告日志，放入WEB-INF/logs文件夹里面
13.教师老师使用页面模板为gzhmc\cpts_161_bet，管理员使用模板为gzhmc\matrix-admin01
 	// 判断学生
	private int STUDENT_ROLE = 3;
	// 判断教师
	private int TEACHER_ROLE = 2;
	// 判断管理员权限
	private int MANAGE_ROLE = 1;
	// 判断教师账号是否已经被验证
	private int TEACHER_VERIFY = 1;

跟新版注意事项：
1.在布置服务器时需要在server.xml文件添加       <Context docBase="ueditor" path="/ueditor" reloadable="true" source="org.eclipse.jst.jee.server:ueditor"/>
 <Context  path="/ueditor" docBase="D:\java\tomcat\apache-tomcat-8.0.35\webapps\ueditor" debug="0"  privileged="true" 
reloadable="true"/>
其中source看具体情况更改配置，作用是ueditor访问项目外文件夹里面的图片,同时要在与项目平行的文件夹内新建ueditor文件
2.部署项目时，修改文件上传绝对路径和下载绝对路径,修改查看word文档绝对路径
3.在布置服务器时需要在server.xml文件设置  <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" maxPostSize="-1" maxSavePostSize="-1" maxHttpHeaderSize ="1024000" />
 report cstatu:0:未批改，1：已批改 2：不及格允许再次提交（中间状态）3：允许再次提交4：不及格
 4.jacob.jar需要放在tomcat，lib下
 5:cTopicStatus=0表示为审核，为1 为通过
 
ie内核不支持iframe onload刷新子窗口，在父窗口onload完时，子窗口还未onload，故会造成一系列js异常
