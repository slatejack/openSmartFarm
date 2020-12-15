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
![](https://github.com/slatejack/openSmartFarm/blob/main/buildimg/%E9%A2%84%E8%A7%881.jpg?raw=true)
![](https://github.com/slatejack/openSmartFarm/blob/main/buildimg/%E9%A2%84%E8%A7%882.jpg?raw=true)
![](https://github.com/slatejack/openSmartFarm/blob/main/buildimg/%E9%A2%84%E8%A7%883.jpg?raw=true)

## 加入开发组
由于目前大部分精力还是放在开发学校的农场app上，所以框架的内容可能做不到及时更新，如有意愿可私信或发送邮件至1815846446@qq.com。如是本校师生想加入农场app的开发也可以发送信息至上方联系方式，感谢支持！

