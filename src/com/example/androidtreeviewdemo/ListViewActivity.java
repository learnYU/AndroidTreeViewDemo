package com.example.androidtreeviewdemo;

import java.util.ArrayList;

import com.example.androidtreeviewdemo.treeview.Element;
import com.example.androidtreeviewdemo.treeview.TreeViewAdapter;
import com.example.androidtreeviewdemo.treeview.TreeViewItemClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ListViewActivity extends Activity {

	private ListView treeview;
	/** ���е�Ԫ�ؼ��� */
	private ArrayList<Element> elements;
	/** ����ԴԪ�ؼ��� */
	private ArrayList<Element> elementsData;
	private EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treeview_activity);
		
		LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
		init();
		editText = (EditText) findViewById(R.id.editText);
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String string = editText.getText().toString();
				System.out.println(string);
				
				for (int i = 0; i < elementsData.size(); i++) {
					Element element = elementsData.get(i);
					
					System.out.println(element.getContentText());
					
					if (element.getContentText().contains(string)) {
						element.setExpanded(true);
						System.out.println(element);
					}
					
				}
			}
		});
		
		treeview = (ListView) findViewById(R.id.treeview);
		
		TreeViewAdapter treeViewAdapter = new TreeViewAdapter(elements, elementsData, inflater);
		TreeViewItemClickListener treeViewItemClickListener = new TreeViewItemClickListener(treeViewAdapter);
		
		treeview.setAdapter(treeViewAdapter);
		treeview.setOnItemClickListener(treeViewItemClickListener);
		
	}
	
	/**
	 * ���ؼ�����
	 */
	private void init() {
		elements = new ArrayList<Element>();
		elementsData = new ArrayList<Element>();
		
		//���ӽڵ�  -- �ڵ����ƣ��ڵ�level���ڵ�id�����ڵ�id���Ƿ����ӽڵ㣬�Ƿ�չ��
		
		//���������ڵ�
		Element e1 = new Element("ɽ��ʡ", Element.TOP_LEVEL, 0, Element.NO_PARENT, true, false);
		
		//���ӵ�һ��ڵ�
		Element e2 = new Element("�ൺ��", Element.TOP_LEVEL + 1, 1, e1.getId(), true, false);
		//���ӵڶ���ڵ�
		Element e3 = new Element("������", Element.TOP_LEVEL + 2, 2, e2.getId(), true, false);
		//���ӵ�����ڵ�
		Element e4 = new Element("�����·", Element.TOP_LEVEL + 3, 3, e3.getId(), false, false);
		
		//���ӵ�һ��ڵ�
		Element e5 = new Element("��̨��", Element.TOP_LEVEL + 1, 4, e1.getId(), true, false);
		//���ӵڶ���ڵ�
		Element e6 = new Element("֥���", Element.TOP_LEVEL + 2, 5, e5.getId(), true, false);
		//���ӵ�����ڵ�
		Element e7 = new Element("���̨�ֵ�", Element.TOP_LEVEL + 3, 6, e6.getId(), false, false);
		
		//���ӵ�һ��ڵ�
		Element e8 = new Element("������", Element.TOP_LEVEL + 1, 7, e1.getId(), false, false);

		//���������ڵ�
		Element e9 = new Element("�㶫ʡ", Element.TOP_LEVEL, 8, Element.NO_PARENT, true, false);
		//���ӵ�һ��ڵ�
		Element e10 = new Element("������", Element.TOP_LEVEL + 1, 9, e9.getId(), true, false);
		//���ӵڶ���ڵ�
		Element e11 = new Element("��ɽ��", Element.TOP_LEVEL + 2, 10, e10.getId(), true, false);
		//���ӵ�����ڵ�
		Element e12 = new Element("���ϴ��", Element.TOP_LEVEL + 3, 11, e11.getId(), true, false);
		//���ӵ��Ĳ�ڵ�
		Element e13 = new Element("10000��", Element.TOP_LEVEL + 4, 12, e12.getId(), true, true);
		//���ӵ��Ĳ�ڵ�
		Element e14 = new Element("666", Element.TOP_LEVEL + 5, 14, e13.getId(), false, false);
		
		//���ӳ�ʼ��Ԫ��
		elements.add(e1);
		elements.add(e9);
		//��������Դ
		elementsData.add(e1);
		elementsData.add(e2);
		elementsData.add(e3);
		elementsData.add(e4);
		elementsData.add(e5);
		elementsData.add(e6);
		elementsData.add(e7);
		elementsData.add(e8);
		elementsData.add(e9);
		elementsData.add(e10);
		elementsData.add(e11);
		elementsData.add(e12);
		elementsData.add(e13);
		elementsData.add(e14);
	}
	
}