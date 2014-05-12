package us.codecraft.webmagic.example;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.pipeline.JsonFilePageModelPipeline;
import us.codecraft.webmagic.utils.Experimental;

/**
 * Created by james on 14-5-11.
 */
@Experimental
public class XiaChuFang {

    @ExtractBy(type = ExtractBy.Type.Css, value = "h1.page-title")
    private String pageTitle;

    @ExtractBy(type = ExtractBy.Type.XPath, value = "//div[@class='desc']")
    private String description;

    @ExtractBy(type = ExtractBy.Type.XPath, value = "//div[@class='cover image expandable block-negative-margin']")
    private String coverImg;

    @ExtractBy(type = ExtractBy.Type.XPath, value = "//div[@class='ings']")
    private String material;

    @ExtractBy(type = ExtractBy.Type.XPath, value = "//div[@class='steps']")
    private String step;

    @ExtractBy(type = ExtractBy.Type.XPath, value = "//div[@class='tip-container']")
    private String tip;


}
