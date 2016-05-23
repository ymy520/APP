package cn.elife.bean;

/**
 * Created by 叶梦雅 on 2016/5/18.
 */
public class SnacksBanner {
    private String id; // 图片id
    private String imgUrl; // 图片url
    private boolean isAd;
    private String startTime;
    private String endTime;
    private int width; // 图片宽度
    private int height; // 图片高度
    private boolean available; // 是否可见

    public SnacksBanner() {
    }

    public SnacksBanner(String id, String imgUrl, boolean isAd,
                        String startTime, String endTime, int width,
                        int height, boolean available) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.isAd = isAd;
        this.startTime = startTime;
        this.endTime = endTime;
        this.width = width;
        this.height = height;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isAd() {
        return isAd;
    }

    public void setAd(boolean ad) {
        isAd = ad;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "SnacksBanner{" +
                "id='" + id + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", isAd=" + isAd +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", available=" + available +
                '}'+"\n";
    }
}
