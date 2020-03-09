package com.example.linkspc.appnhac.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

    @SerializedName("IdAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("TenAlbum")
    @Expose
    private String tenAlbum;
    @SerializedName("TencasiAblbum")
    @Expose
    private String tencasiAblbum;
    @SerializedName("HinhanhAlbum")
    @Expose
    private String hinhanhAlbum;

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        this.tenAlbum = tenAlbum;
    }

    public String getTencasiAblbum() {
        return tencasiAblbum;
    }

    public void setTencasiAblbum(String tencasiAblbum) {
        this.tencasiAblbum = tencasiAblbum;
    }

    public String getHinhanhAlbum() {
        return hinhanhAlbum;
    }

    public void setHinhanhAlbum(String hinhanhAlbum) {
        this.hinhanhAlbum = hinhanhAlbum;
    }

}