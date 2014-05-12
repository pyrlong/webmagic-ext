package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by james on 14-5-11.
 */
public class XiaChuFangProcessor implements PageProcessor {

    public static final String URL_LIST = "http://www\\.xiachufang\\.com/category/\\d+";

    public static final String URL_POST = "http://www\\.xiachufang\\.com/recipe/\\d+";

    private Site site = Site
            .me()
            .setDomain("www.xiachufang.com")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            List<String> posts = page.getHtml().links().regex(URL_POST).all();
            System.out.println("Add posts : " + posts.size());
            page.addTargetRequests(posts);
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            //文章页
        } else {
            page.putField("pageTitle", page.getHtml().xpath("//h1[@class='page-title"));
            //page.putField("description", page.getHtml().xpath("//div[@class='desc']"));
            //page.putField("coverImg", page.getHtml().xpath("//div[@class='cover image expandable block-negative-margin']"));
            //page.putField("material", page.getHtml().xpath("//div[@class='ings']"));
            //page.putField("step", page.getHtml().xpath("//div[@class='steps']"));
            //page.putField("tip", page.getHtml().xpath("//div[@class='tip-container']"));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new XiaChuFangProcessor()).addPipeline(new ConsolePipeline()).addUrl("http://www.xiachufang.com/category/731/").thread(3).run();
    }
}

