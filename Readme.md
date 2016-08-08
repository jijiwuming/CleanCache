![铃屋什造](./铃屋什造.png)
#使用说明
##1.引入cordova.js
```
	<script src="cordova.js"></script>
```
##2.执行脚本
```javascript
cordova.exec(function(message) {
                     alert('successed');
                }, function(message) {
                     alert('failed');
                }, "CleanCache", "CleanCache", ["io.jijiwuming.github.Clear"]);

```
##附加说明
该插件适用于ios及android双端，
ios端清理tmp(临时文件),cache(缓存文件),responsecache(响应缓存)
android清理webview缓存,应用缓存(递归删除)
不会清理localstorage