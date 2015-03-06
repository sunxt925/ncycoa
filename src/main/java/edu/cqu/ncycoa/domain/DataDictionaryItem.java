package edu.cqu.ncycoa.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NCYCOA_DATA_DICT_ITM")
public class DataDictionaryItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ITEM_ID")
	private Long id;
	
	@Column(name="ITEM_CODE")
	private String code;
	
	@Column(name="ITEM_NAME")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DICT_ID")
	private DataDictionary dictionary;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private DataDictionaryItem parent;
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DataDictionaryItem> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataDictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(DataDictionary type) {
		this.dictionary = type;
	}

	public DataDictionaryItem getParent() {
		return parent;
	}

	public void setParent(DataDictionaryItem parent) {
		this.parent = parent;
	}

	public List<DataDictionaryItem> getChildren() {
		return children;
	}

	public void setChildren(List<DataDictionaryItem> children) {
		this.children = children;
	}
	
}