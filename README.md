# 智慧农业开源框架 with Android
</br>

## 这是什么？
该框架为四川大学锦城学院智慧农场Android端去敏之后的app框架，由个人开发完成，希望通过后续不同的迭代使这套框架能适用于绝大部分智能农业app端的开发甚至智慧物联网系统的开发，由于该项目目前只有作者一人进行编写与维护，希望各位大佬们能多多包涵，发现bug请私信或发送邮件至1815846446@qq.com。

## 我该怎么用？
以下为目前主要类所代表的含义
|  功能   | 代码实现  | 注释 |
|  ----  | ----  | ---- |
| 主功能页 | MainActivity.java | 用户进入主界面，bar切换不同功能页 |
| 农场列表  | FarmListFragment.java | 功能页1，用于显示农场列表及天气情况 |
| 农场状态&设备控制（按农场分）  | FarmDetails.java | 农场详情页，显示该农场的传感器数据及控制器状态与控制控制器 |
| 设备列表 | EquipmentFragment.java | 功能页2，用于显示农场设备类型 |
| 设备详情 | EquipmentDetails.java | 设备详情页，显示所有农场该类设备的状态与控制 |
</br>
操作步骤嘛，git clone到本地，用Android studio打开，等待编译，成功后便可开始使用啦。</br>

>其中由于脱敏的缘故，将部分代码逻辑进行了修改，在后期部署到真实环境中时请按照代码中的注释进行修改，此外本软件所使用的图标为@iconfont.cn上上传的免费图标，请悉知。
</br>

使用的第三方库：
|  功能   | 项目地址  |
|  ----  | ----  |
| Kongzue Dialog V3 | [项目地址](https://github.com/kongzue/DialogV3) |
| BubbleSeekBar  | [项目地址](https://github.com/woxingxiao/BubbleSeekBar) |
| Gson | [项目地址](https://github.com/google/gson) |
|org.eclipse.paho:org.eclipse.paho.client.mqttv3| |
|org.eclipse.paho:org.eclipse.paho.android.service| |
</br>

## 效果截图
以下项目图皆为锦城学院智慧农场app图
![](https://github.com/slatejack/openSmartFarm/blob/main/buildimg/%E9%A2%84%E8%A7%881_1.jpg?raw=true)
![](https://github.com/slatejack/openSmartFarm/blob/main/buildimg/%E9%A2%84%E8%A7%882_1.jpg?raw=true)
![](https://github.com/slatejack/openSmartFarm/blob/main/buildimg/%E9%A2%84%E8%A7%883_1.jpg?raw=true)
![](https://github.com/slatejack/openSmartFarm/blob/main/buildimg/%E9%A2%84%E8%A7%884_1.jpg?raw=true)
![](https://github.com/slatejack/openSmartFarm/blob/main/buildimg/%E9%A2%84%E8%A7%885_1.jpg?raw=true)

## 开发日志
|  版本号   | 实现功能  | 注释 |
|  ----  | ----  | ---- |
|  V2.0.0  | UI绘制，界面复现，已集成部分操作控制逻辑，等待真实数据接收 | |
|  V2.0.1  | 新增驱鸟器控制模块，集成MQTT通信模块，已可实现农场数据接收及控制 | 基本功能已完成 |
|  V2.0.2  | 新增获取传感器历史数据消息 |
|  V2.0.3  | 新增设置界面强制竖屏，防止切屏导致的数据重传 |
|  V2.0.4  | 主页ListView替换为RecycleView，adapter已替换 |
|  V2.0.5  | 关于页新增设置菜单，包含消息通知（目前暂不使用）和检查更新两个设置，更新检测已集成 |
|  V2.0.6  | 新增用户注册界面（未实装），添加农场界面，添加设备界面 |
|  V2.0.7  | 登录方式由本地验证转为云端验证登录，优化天气界面UI，修复部分BUG,集成小米推送（MIPush） |
|  V2.0.8  | 新增个人界面，替换原关于设置界面，新增实验室签到界面，美化布局 |
|  V2.0.8.1  | 新增请求常驻系统后台权限对话框 |
|  V2.0.8.2  | 二维码生成加密方式变更,更正二维码加密内容 |
|  V2.0.8.3  | 新增签到历史界面 |
|  V2.0.9  | 新增设置签到界面，新增两种签到方式，优化用户界面 |
|  V2.0.9.1  | 新增取消签到界面，完成立即签到和取消签到实现逻辑，优化用户界面 |
|  V2.0.9.2  | 新增实名认证界面，完成人脸拍照界面逻辑，待完成提交实名信息后台接口 |
|  V2.0.11 | beta版发布 |
|  V2.0.11.1  |下发签到时会通知所有用户，农场控制按钮UI更新，实现开启与关闭按钮阴影的改变| 已知在MIUI12.0.8系统上无法拍照上传人像 |
|  V2.0.12 | 更换MQTT服务器，beta 2 | 
|  V2.0.13 | 更新签到历史记录列表动画及实现recycleView动态加载，减少性能消耗 | EMUI11.0.0.168版本（MRX-W29）无法拍照上传，同MIUI12.0.8|
|  V2.0.13.1 | 新增农场控制界面权限管理（学生权限的用户无法操作农场设备），新增消息列表入口，修改用户注销时清库的逻辑(清空用户签到历史数据库) |
|  V2.0.13.2 | 新增分类控制界面权限管理（学生权限的用户无法操作设备）|
|  V2.0.13.3 | 设置签到部分变更为公网域名可访问,新增预约签到界面（未实装）,农场详情界面新增等待数据动画 | 更改本地git用户 |
|  V2.0.13.4 | 重绘用户登录页，注册页,填写密码页，beta 3 |
|  V2.0.14  | 集成视频监控（萤石云）与控制，优化界面控制逻辑，开启软件更新监测，beta 4 |
|  V2.0.14.1 | 修改视频监控界面按钮样式，登录界面优化登录状态提示，开启注册界面 |
|  V2.0.15  | 修改设备种类界面布局（recycleView），优化列表加载  |
|  V2.0.15.1 | 更新请求版本更新的接口地址，优化版本更新对话框，加入按钮震动反馈，新增水波纹效果，监控界面可在竖屏对摄像头进行操作 |
|  V2.1.0 | 更新数据库，后台接口地址，启用注册邀请码功能，新增登录验证码，更改权限分级，细分各级权限控制内容，优化更新所有输入框边界值，版本2.0正式更新为2.1 |
|  V2.1.0.1 | 修改http请求逻辑，支持中文字段传输，修改部分返回值判断逻辑 |
|  V2.1.1 | 新增帮助反馈入口，修复二维码发起签到成功后不会返回上一级的错误 | |
|  V2.1.2 | 修复连续点击主页tap栏同一tap可能导致的应用闪退，优化注册登录时提示文本，修改部分边界值 |MIUI更新12.0.5系统后已可以正常拍照并上传人脸|
|  V2.1.3 | 新增邀请用户入口（暂只有Administrator用户可邀请）| |
|  V2.1.3.1 | 更新邀请用户邮件发送后台逻辑，增加token校验，新增学生专业选择| |
|  V2.1.4.1 | 用户实名认证接口更新（暂未开放实名认证），新增学生学院专业信息（实名认证界面），优化部分spinner加载动画 | |
|  V2.1.5 | 用户操作视频接口变更，现接口仅允许同时一人控制摄像头，其余用户仅可观看 | |
|  V2.1.6 | 新增用户登录设备唯一性校验，当前账号仅允许一个设备同时登录，信息绑定接口开放测试（beta）| |
|  V2.1.6.1 | 用户实名认证界面UI改动 | |


## 加入开发组
由于目前大部分精力还是放在开发学校的农场app上，所以框架的内容可能做不到及时更新，如有意愿可私信或发送邮件至1815846446@qq.com。如是本校师生想加入农场app的开发也可以发送信息至上方联系方式，感谢支持！

