package com.dev.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class CreateIndex {
	//创建索引
	@Test
	public void testCreateIndex() throws IOException {

		//1,创建文档
		Document document = new Document();

		//2,给文档添 加字段，字段信息的参数：字段名称，字段内容，是否存储，Store.YES表示存储，Store.NO表示不存储
		document.add(new StringField("id", "1", Field.Store.YES));
		document.add(new TextField("title","谷歌地图之父跳槽facebook", Field.Store.YES));

		//3,创建目录对象，使用相对目录indexDir
		Directory directory = FSDirectory.open(new File("indexDir"));

		//4，创建分词器对象，这里我们使用标准分词器来代替 ChineseAnalyzer，(PS：ChineseAnalyzer已经过时)
		Analyzer analyzer = new StandardAnalyzer();

		//5,创建文档配置对象,参数：lucene的版本，依赖的分词器对象
		IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, analyzer);

		//6,创建索引写入对象，参数：写入目录，及写入配置
		IndexWriter indexWriter = new IndexWriter(directory, conf);

		//7,写入索引
		indexWriter.addDocument(document);

		//8,提交
		indexWriter.commit();

		//9,关闭
		indexWriter.close();
	}
}
