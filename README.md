执行js修改标签属性
((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "display: block");

禁止所有视频
((JavascriptExecutor) driver).executeScript("videos = document.querySelectorAll(\"video\"); for(video of videos) {video.pause()}");

获取谷歌浏览器的所有cookie
options.addArguments("user-data-dir=C:\\Users\\xx\\AppData\\Local\\Google\\Chrome\\User Data\\Default");

多class定位，B站为例
driver.findElements(By.cssSelector(".webcast-chatroom___item.webcast-chatroom___enter-done"));

精准定位
webElement1 = elements.get(i).findElement(By.cssSelector("span[class$='with-emoji-text']"));

抖音需要添加header才能绕过反爬
options.addArguments("User-agent=xxxxx")
