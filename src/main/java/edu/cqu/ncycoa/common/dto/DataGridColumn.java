package edu.cqu.ncycoa.common.dto;

/**
 * �б��ֶ�ģ��
 */
public class DataGridColumn {

	protected String title; 		// �������
	protected String field; 		// ���ݿ��Ӧ�ֶ�
	protected Integer width; 		// ���
	protected String rowspan; 		// ����
	protected String colspan; 		// ����
	protected String align; 		// ���뷽ʽ
	protected boolean sortable; 	// �Ƿ�����
	protected boolean checkbox; 	// �Ƿ���ʾ��ѡ��
	protected boolean image; 		// �Ƿ���ͼƬ
	protected String imageSize; 	// �Զ���ͼƬ��ʾ��С
	protected String dateFormatter; // ���ڸ�ʽ����
	protected boolean hidden; 		// �Ƿ�����
	protected boolean query; 		// �Ƿ��ѯ
	protected String queryMode = "single"; 	 // ��ѯģʽ��single���ֶβ�ѯ��group��Χ��ѯ
	protected boolean autoLoadData = true; 	 // �б��Ƿ��Զ���������
	protected boolean frozen = false;  // �Ƿ��Ǳ�����, Ĭ�ϲ���
	protected String url; 					 // �Զ�������
	protected String funname = "openwindow"; // �Զ��庯������
	protected String dictionary;
	protected String replace;
	protected String extend;
	protected String style; 		// �е���ɫֵ
	protected String downloadName; 	// ��������
	protected boolean autocomplete; // �Զ���ȫ
	protected String extendParams; 	// ��չ����,easyui�еĵ��˴�û�е�

	public String getDownloadName() {
		return downloadName;
	}

	public void setDownloadName(String downloadName) {
		this.downloadName = downloadName;
	}

	public String getImageSize() {
		return imageSize;
	}

	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}

	public boolean isQuery() {
		return query;
	}

	public void setQuery(boolean query) {
		this.query = query;
	}

	public boolean isImage() {
		return image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public void setDateFormatter(String formatter) {
		this.dateFormatter = formatter;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getTitle() {
		return title;
	}

	public String getField() {
		return field;
	}

	public Integer getWidth() {
		return width;
	}

	public String getRowspan() {
		return rowspan;
	}

	public String getColspan() {
		return colspan;
	}

	public String getAlign() {
		return align;
	}

	public boolean isSortable() {
		return sortable;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public String getDateFormatter() {
		return dateFormatter;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFunname() {
		return funname;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}

	public String getDictionary() {
		return dictionary;
	}

	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}

	public String getQueryMode() {
		return queryMode;
	}

	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}

	public String getReplace() {
		return replace;
	}

	public void setReplace(String replace) {
		this.replace = replace;
	}

	public boolean isAutoLoadData() {
		return autoLoadData;
	}

	public void setAutoLoadData(boolean autoLoadData) {
		this.autoLoadData = autoLoadData;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozenColumn) {
		this.frozen = frozenColumn;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public boolean isAutocomplete() {
		return autocomplete;
	}

	public void setAutocomplete(boolean autocomplete) {
		this.autocomplete = autocomplete;
	}

	public String getExtendParams() {
		return extendParams;
	}

	public void setExtendParams(String extendParams) {
		this.extendParams = extendParams;
	}

}
