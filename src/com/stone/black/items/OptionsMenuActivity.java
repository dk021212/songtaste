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

		// �����Ա�˵���
		SubMenu subMenuGender = menu.addSubMenu(MAIN_GROUP, MENU_GENDER,
				index + 1, R.string.black_gender);
		subMenuGender.setIcon(R.drawable.black_gender);
		subMenuGender.setHeaderIcon(R.drawable.black_gender);

		// Ϊ�Ա��Ӳ˵���Ӳ˵�����
		mMale = subMenuGender.add(GENDER_GROUP, MENU_GENDER_MALE, index + 1,
				R.string.black_male);
		// ����Ĭ��ֵ����ѡ�е�״̬
		mMale.setChecked(true);
		subMenuGender.add(GENDER_GROUP, MENU_GENDER_FEMALE, index + 2,
				R.string.black_female);
		// ����GENDER_GROUP�黥�⣬��������һ�鵥ѡ������Ϊ�����
		subMenuGender.setGroupCheckable(GENDER_GROUP, true, true);
		menu.setGroupVisible(GENDER_GROUP, true);

		// �������ò˵���
		SubMenu subMenuHobby = menu.addSubMenu(MAIN_GROUP, MENU_HOBBY,
				index + 2, R.string.black_hobby);
		subMenuHobby.setIcon(R.drawable.black_hobby);
		subMenuHobby.setHeaderIcon(R.drawable.black_hobby);

		mHobbies[0] = subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY_BASKETBALL,
				index + 1, R.string.black_basketball);
		// Ϊ������Ӹ�ѡ��ť
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

		// ���ÿ�ݼ�
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
		StringBuffer result = new StringBuffer("��ѡ����Ա�Ϊ��");
		if (mMale.isChecked()) {
			result.append("��");
		} else {
			result.append("Ů");
		}

		StringBuffer hobbyStr = new StringBuffer();
		for (MenuItem item : mHobbies) {
			if (item.isChecked()) {
				hobbyStr.append(item.getTitle() + "��");
			}
		}

		if (hobbyStr.length() > 0) {
			result.append("�����İ���Ϊ��")
					.append(hobbyStr.substring(0, hobbyStr.length() - 1))
					.append("��\n");
		} else {
			result.append("��\n");
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

			// MenuBuilderʵ��Menu�ӿڣ������˵�ʱ����������menu��ʵ����MenuBuilder����(java�Ķ�̬����)
			m.invoke(menu, enable);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
