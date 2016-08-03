# FloatingView For android
a floatingView can move to anywhere,and can click to another pager,we can control its showing time 、position and its background.

# Demo
![](https://github.com/Kingnewspring/FloatingView/raw/master/floatingview.gif)

#Use
### New a floatingView
```Java
floatingView = new FloatingView(MainActivity.this);
```
### Show time
```Java
floatingView.setIcon_staty(10);
```
### Show the percentage to the top of the screen
```Java
floatingView.setIcon_top(50);
```
### Show the percentage to the right of the screen
```Java
floatingView.setIcon_right(20);
```
### Action of your click
```Java
floatingView.setclick_action("http://www.baidu.com");
```
### Show the floatingView on your activity
```Java
floatingView.show(R.mipmap.demo);
```
### Stop show
```Java
floatingView.stop();
```
