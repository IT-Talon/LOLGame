package com.lol.lolgame.Bean;

import org.json.JSONObject;

/**
 * Created by Talon on 2016/10/5.
 * Talon
 */

public class VideoBean {


    /**
     * id : 115
     * name : 最新
     * desc : 汇集每日最新的娱乐视频
     * pic : lol/vlist/115h51fa1b1e.jpg
     * pic_url : http://pic.woqugame.com/lol/vlist/115h51fa1b1e.jpg
     */

    private String id;
    private String name;
    private String desc;
    private String pic;
    private String pic_url;

    public void setJson(JSONObject obj){
        setId(obj.optString("id"));
        setDesc(obj.optString("desc"));
        setName(obj.optString("name"));
        setPic(obj.optString("pic"));
        setPic_url(obj.optString("pic_url"));
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", pic='" + pic + '\'' +
                ", pic_url='" + pic_url + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
