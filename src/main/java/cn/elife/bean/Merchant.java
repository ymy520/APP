package cn.elife.bean;

/**
 * Created by wgyscsf on 2016/5/14.
 */
public class Merchant {
    private String id;
    private String url;
    private String title;
    private float  rateBar;
    private String begin;
    private int nums;

    public Merchant(int nums, String id, String url, String title, float rateBar, String begin) {
        this.nums = nums;
        this.id = id;
        this.url = url;
        this.title = title;
        this.rateBar = rateBar;
        this.begin = begin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRateBar() {
        return rateBar;
    }

    public void setRateBar(float rateBar) {
        this.rateBar = rateBar;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", rateBar=" + rateBar +
                ", begin='" + begin + '\'' +
                ", nums=" + nums +
                '}';
    }
}
