package com.lipan.crud.bean;

public class PoiData {
    private Integer poiid;

    private String poilng;

    private String poilat;

    private Integer fileid;

    private String description;

    @Override
	public String toString() {
		return "PoiData [poiid=" + poiid + ", poilng=" + poilng + ", poilat=" + poilat + ", fileid=" + fileid
				+ ", description=" + description + "]";
	}

	public PoiData(Integer poiid, String poilng, String poilat, Integer fileid, String description) {
		super();
		this.poiid = poiid;
		this.poilng = poilng;
		this.poilat = poilat;
		this.fileid = fileid;
		this.description = description;
	}

	public PoiData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPoiid() {
        return poiid;
    }

    public void setPoiid(Integer poiid) {
        this.poiid = poiid;
    }

    public String getPoilng() {
        return poilng;
    }

    public void setPoilng(String poilng) {
        this.poilng = poilng == null ? null : poilng.trim();
    }

    public String getPoilat() {
        return poilat;
    }

    public void setPoilat(String poilat) {
        this.poilat = poilat == null ? null : poilat.trim();
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}