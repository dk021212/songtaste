package com.stone.black.items;

import java.lang.reflect.Method;

import com.stone.songtaste.R;

import static com.stone.black.items.MenuFinalValues.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.widget.EditText;

public class OptionsMenuActivity extends Activity {

	private MenuItem mMale = null;
	private MenuItem[] mHobbies = new MenuItem[3];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.black_options_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		int index = Menu.FIRST;

		setIconEnable(menu, true);

		// 建立性别菜单组
		SubMenu subMenuGender = menu.addSubMenu(MAIN_GROUP, MENU_GENDER,
				index + 1, R.string.black_gender);
		subMenuGender.setIcon(R.drawable.black_gender);
		subMenuGender.setHeaderIcon(R.drawable.black_gender);

		// 为性别子菜单添加菜单内容
		mMale = subMenuGender.add(GENDER_GROUP, MENU_GENDER_MALE, index + 1,
				R.string.black_male);
		// 设置默认值，即选中的状态
		mMale.setChecked(true);
		subMenuGender.add(GENDER_GROUP, MENU_GENDER_FEMALE, index + 2,
				R.string.black_female);
		// 设置GENDER_GROUP组互斥，即该组是一组单选（可以为多个）
		subMenuGender.setGroupCheckable(GENDER_GROUP, true, true);
		menu.setGroupVisible(GENDER_GROUP, true);

		// 建立爱好菜单组
		SubMenu subMenuHobby = menu.addSubMenu(MAIN_GROUP, MENU_HOBBY,
				index + 2, R.string.black_hobby);
		subMenuHobby.setIcon(R.drawable.black_hobby);
		subMenuHobby.setHeaderIcon(R.drawable.black_hobby);

		mHobbies[0] = subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY_BASKETBALL,
				index + 1, R.string.black_basketball);
		// 为此项添加复选按钮
		mHobbies[0].setCheckable(true);
		mHobbies[0].setChecked(true);
		mHobbies[1] = subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY_FOOTBALL,
				index + 2, R.string.black_football);
		mHobbies[1].setCheckable(true);
		mHobbies[2] = subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY_VOLLEYBALL,
				index + 3, R.string.black_volleyball);
		mHobbies[2].setCheckable(true);

		MenuItem ok = menu.add(GENDER_GROUP + 2, MENU_OK, index + 3,
				R.string.black_OK);

		OnMenuItemClickListener listener = new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				getSubMenuStr();
				return true;
			}

		};

		ok.setOnMenuItemClickListener(listener);

		// 设置快捷键
		ok.setAlphabeticShortcut('o');
		return true;
		
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_GENDER_MALE:
		case MENU_GENDER_FEMALE:
			item.setChecked(true);
			getSubMenuStr();
			break;
		case MENU_HOBBY_BASKETBALL:
		case MENU_HOBBY_FOOTBALL:
		case MENU_HOBBY_VOLLEYBALL:
			item.setCheckable(item.isChecked());
			getSubMenuStr();
			break;
		}

		return true;
		

	}

	protected void getSubMenuStr() {
		StringBuffer result = new StringBuffer("您选择的性别为：");
		if (mMale.isChecked()) {
			result.append("男");
		} else {
			result.append("女");
		}

		StringBuffer hobbyStr = new StringBuffer();
		for (MenuItem item : mHobbies) {
			if (item.isChecked()) {
				hobbyStr.append(item.getTitle() + "，");
			}
		}

		if (hobbyStr.length() > 0) {
			result.append("，您的爱好为：")
					.append(hobbyStr.substring(0, hobbyStr.length() - 1))
					.append("。\n");
		} else {
			result.append("。\n");
		}

		EditText etText = (EditText) findViewById(R.id.editText);
		etText.append(result);
	}

	@SuppressWarnings("unused")
	private void setIconEnable(Menu menu, boolean enable) {
		try {
			Class<?> clazz = Class
					.forName("com.android.internal.view.menu.MenuBuilder");
			Method m = clazz.getDeclaredMethod("setOptionalIconsVisible",
					boolean.class);
			m.setAccessible(true);

			// MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
			m.invoke(menu, enable);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
