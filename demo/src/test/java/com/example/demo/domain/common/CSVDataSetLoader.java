package com.example.demo.domain.common;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

/**
 * データベーステスト用のCSVローダークラス
 * @author kiyotah
 *
 */
public class CSVDataSetLoader extends AbstractDataSetLoader {
	@Override
	protected IDataSet createDataSet(Resource resource) throws Exception {
		return new CsvDataSet(resource.getFile());
	}
}
