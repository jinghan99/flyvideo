package top.jinghan94.flyvideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 定义了一个Toolbar控件，这个控件是由appcompat库提供的。这里我们给Toolbar指定
了一个id，将它的宽度设置为match_parent，高度设置为actionBar的高度，背景色设置为
colorPrimary。不过下面的部分就稍微有点难理解了，由于我们刚才在styles.xml中将程序的
主题指定成了浅色主题，因此Toolbar现在也是浅色主题，那么Toolbar上面的各种元素就会自
动使用深色系，从而和主体颜色区别开。但是之前使用ActionBar时文字都是白色的，现在变成
黑色的会很难看。那么为了能让Toolbar单独使用深色主题，这里我们使用了android:theme
属性，将Toolbar的主题指定成了ThemeOverlay.AppCompat.Dark.ActionBar。但是这样指
定之后又会出现新的问题，如果Toolbar中有菜单按钮（我们在3.2.5小节中学过），那么弹出
的菜单项也会变成深色主题，这样就再次变得十分难看了，于是这里又使用了
app:popupTheme属性，单独将弹出的菜单项指定成了浅色主题。
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
    }
}