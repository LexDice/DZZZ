package TestHTMLtoXML;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.tidy.Configuration;
import org.w3c.tidy.Tidy;


import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedWriter;
import java.io.FileInputStream;



public class htox {
    private String url;
    private String outFileName;
    private String errOutFileName;

    public htox(String url, String outFileName, String errOutFileName) {
        this.url = url; //目标页面地址
        this.outFileName = outFileName; //输出文件的地址和名称
        this.errOutFileName = errOutFileName; //输出错误文件的地址和名称
    }
    
    private void convert(String result) {

        Tidy tidy = new Tidy();
        tidy.setXmlOut(true); 
        tidy.setXmlPi(true);   //添加 <?xml?> 标签 为输出的 XML 文件， 这些参数是可选的。
        tidy.setXmlSpace(true);
        tidy.setQuoteNbsp(false);
        tidy.setXmlSpace(false);
        tidy.setCharEncoding(Configuration.UTF8);
        //tidy.setInputEncoding("utf-8");

        try {
        	tidy.setConfigurationFromFile("config.txt");
            tidy.setErrout(new PrintWriter(new FileWriter(errOutFileName), true));
            FileWriter fw = new FileWriter("output.html");
            BufferedWriter bw = new BufferedWriter(fw);
            result = StrClass.changeStr("(<script[\\s\\S]*?</script>)|(rel=\"[\\s\\S]*?\")|(<!DOCTYPE[\\s\\S]*?>)|(xmlns=\"[\\s\\S]*?\")|(<svg[\\s\\S]*?</svg>)|(<cache[\\s\\S]*?>)|(</cache[\\s\\S]*?>)","",result);
            Pattern pattern = Pattern.compile("<[^>]*?<");
            Matcher matcher = pattern.matcher(result);
            while(matcher.find())
            	result = StrClass.changeStr(matcher.group(),matcher.group().substring(0,matcher.group().length()-2) + "><",result);
            
            result = StrClass.changeStr("<span class=\"lang it[\\s\\S]*?</span>","",result);
            result = StrClass.changeStr("<span class=\"lang de[\\s\\S]*?</span>","",result);
            result = StrClass.changeStr("<span class=\"lang fr[\\s\\S]*?</span>","",result);
            result = StrClass.changeStr("<span class=\"lang es[\\s\\S]*?</span>","",result);
            result = StrClass.changeStr("<span class=\"lang pt[\\s\\S]*?</span>","",result);
            result = StrClass.changeStr("<span class=\"lang ru[\\s\\S]*?</span>","",result);
            result = StrClass.changeStr("<span class=\"lang pl[\\s\\S]*?</span>","",result);
            result = StrClass.changeStr("<span class=\"lang gr[\\s\\S]*?</span>","",result);
            
            bw.write(result);
            bw.close();
        	
        	
        	
            FileInputStream in = new FileInputStream("output.html");
            FileOutputStream out = new FileOutputStream(this.outFileName);
            
            tidy.parse(in, out);

            in.close();
            out.close();

        } catch (IOException e) {
            System.out.println(this.toString() + e.toString());
        }
    }
   
    
    
    public static void Html2Xml(String url, String result)
    {
    	if(url == "" || result == "")
    		System.out.println("输入字符串不能为空！！");
    	else{
    		htox t = new htox(url, result, "error.xml");
    		try
    		{
    			Document doc = Jsoup.connect(url) .timeout(30000).get();
    			t.convert(doc.html());
    		} catch (IOException e) {
    			System.out.println(e.toString());
    		}
    		StrClass.change("(<script[\\s\\S]*?</script>)|(rel=\"[\\s\\S]*?\")|(<!DOCTYPE[\\s\\S]*?>)|(xmlns=\"[\\s\\S]*?\")|(<svg[\\s\\S]*?</svg>)|(<cache[\\s\\S]*?>)|(</cache[\\s\\S]*?>)","",result);
    	}
    }
    
    public static void Post2Xml(String url, String result)
    {
    	if(url == "" || result == "")
    		System.out.println("输入字符串不能为空！！");
    	else{
    		htox t = new htox(url, result, "error.xml");
    		WebClient client = new WebClient();
    		client.getOptions().setJavaScriptEnabled(false);
    		client.getOptions().setCssEnabled(false);
    		Page page;
    		try {
    			page = client.getPage(url);
    			String ss = page.getWebResponse().getContentAsString();
    			System.out.println(ss);
    			t.convert(ss);
    		} catch (IOException e) {
    			System.out.println(e.toString());
    		}
    		StrClass.change("(<script[\\s\\S]*?</script>)|(rel=\"[\\s\\S]*?\")|(<!DOCTYPE[\\s\\S]*?>)|(xmlns=\"[\\s\\S]*?\")|(<svg[\\s\\S]*?</svg>)|(<cache[\\s\\S]*?>)|(</cache[\\s\\S]*?>)","",result);
    	}
    }
}