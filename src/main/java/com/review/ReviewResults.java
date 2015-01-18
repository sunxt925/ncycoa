package com.review;

import java.util.ArrayList;
import java.util.List;

import com.entity.index.Indexitem;
import com.performance.IndexResult;

public class ReviewResults {
	private Indexitem index;
	private String inputStr;
	public double value;
	public double score;
	private List<IndexResult> children = new ArrayList<IndexResult>();
}
