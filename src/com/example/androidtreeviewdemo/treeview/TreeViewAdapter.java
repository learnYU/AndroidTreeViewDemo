package com.example.androidtreeviewdemo.treeview;

import java.util.ArrayList;
import com.example.androidtreeviewdemo.R;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * TreeViewAdapter
 */
public class TreeViewAdapter extends BaseAdapter {
	/** Ԫ������Դ */
	private ArrayList<Element> elementsData;
	/** ����Ԫ�� */
	private ArrayList<Element> elements;
	/** LayoutInflater */
	private LayoutInflater inflater;
	/** item�������������� */
	private int indentionBase;
	
	public TreeViewAdapter(ArrayList<Element> elements, ArrayList<Element> elementsData, LayoutInflater inflater) {
		this.elements = elements;
		this.elementsData = elementsData;
		this.inflater = inflater;
		indentionBase = 80;
	}
	
	public ArrayList<Element> getElements() {
		return elements;
	}
	
	public ArrayList<Element> getElementsData() {
		return elementsData;
	}
	
	@Override
	public int getCount() {
		return elements.size();
	}

	@Override
	public Object getItem(int position) {
		return elements.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint({ "ResourceAsColor", "InlinedApi" }) @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.treeview_item, null);
			holder.disclosureImg = (ImageView) convertView.findViewById(R.id.disclosureImg);
			holder.contentText = (TextView) convertView.findViewById(R.id.contentText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Element element = elements.get(position);
		int level = element.getLevel();
		holder.contentText.setPadding(
				indentionBase * (level - 1), 
				holder.disclosureImg.getPaddingTop(), 
				holder.disclosureImg.getPaddingRight(), 
				holder.disclosureImg.getPaddingBottom());
		if (level == 1) {
//			convertView.setBackgroundColor(android.R.color.holo_blue_dark);
//			holder.contentText.setTextColor(android.R.color.black);
		}
		holder.contentText.setText(element.getContentText());
		
		if (element.isHasChildren() && !element.isExpanded()) {
			holder.disclosureImg.setImageResource(R.drawable.close);
			//����Ҫ��������һ��icon�ɼ�����ΪconvertView�п�����������"�����˲��ɼ�"��view����ͬ��
			holder.disclosureImg.setVisibility(View.VISIBLE);
		} else if (element.isHasChildren() && element.isExpanded()) {
			holder.disclosureImg.setImageResource(R.drawable.open);
			holder.disclosureImg.setVisibility(View.VISIBLE);
		} else if (!element.isHasChildren()) {
			holder.disclosureImg.setImageResource(R.drawable.close);
			holder.disclosureImg.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}
	
	static class ViewHolder{
		ImageView disclosureImg;
		TextView contentText;
	}
}