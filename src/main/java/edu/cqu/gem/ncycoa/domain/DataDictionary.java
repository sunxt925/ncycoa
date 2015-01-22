package edu.cqu.gem.ncycoa.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NCYCOA_DATA_DICT")
public class DataDictionary implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DICT_ID")
	private Long id;
	
	@Column(name="DICT_CODE")
	private String code;
	
	@Column(name="DICT_NAME")
	private String name;
	
	@Column(name="DICT_TYPE")
	private String type;
	
	@Column(name="IS_LOAD")
	private Boolean isLoad;
	
	@OneToMany(mappedBy = "dictionary", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DataDictionaryItem> dictItems;

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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Boolean getIsLoad() {
		return isLoad;
	}

	public void setIsLoad(Boolean isLoad) {
		this.isLoad = isLoad;
	}

	public List<DataDictionaryItem> getDictItems() {
		return dictItems;
	}

	public void setDictItems(List<DataDictionaryItem> dictItems) {
		this.dictItems = dictItems;
	}
	
	private static final long serialVersionUID = 1L;
	private static Map<String, DataDictionary> allDictionarys = new HashMap<String,DataDictionary>();
	private static Map<String, List<DataDictionaryItem>> allDictionaryItems = new HashMap<String,List<DataDictionaryItem>>();
	
	public static Map<String, DataDictionary> getAllDictionarys() {
		return allDictionarys;
	}
	
	public static Map<String, List<DataDictionaryItem>> getAllDictionaryItems() {
		return allDictionaryItems;
	}
	
}