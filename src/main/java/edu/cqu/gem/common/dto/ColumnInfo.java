package edu.cqu.gem.common.dto;

/**
 * ����Ϣ
 * 
 */
public class ColumnInfo {

	/**
	 * ����
	 */
	private String name;
	/**
	 * �Ƿ�����
	 */
	private Boolean regex;
	/**
	 * ����
	 */
	private Boolean searchable;
	/**
	 * ����
	 */
	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * ����
	 */
	private Boolean sortable;

	/**
	 * @return ����
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            ����
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return �Ƿ�����
	 */
	public Boolean getRegex() {
		return regex;
	}

	/**
	 * @param regex
	 *            �Ƿ�����
	 */
	public void setRegex(Boolean regex) {
		this.regex = regex;
	}

	/**
	 * @return ����
	 */
	public Boolean getSearchable() {
		return searchable;
	}

	/**
	 * @param searchable
	 *            ����
	 */
	public void setSearchable(Boolean searchable) {
		this.searchable = searchable;
	}

	/**
	 * @return ����
	 */
	public Boolean getSortable() {
		return sortable;
	}

	/**
	 * @param sortable
	 *            ����
	 */
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

}
