package com.huang.service;

import com.huang.dao.FavoritesMapper;
import com.huang.pojo.Favorites;

import java.util.List;

public class FavoritesServiceImpl implements FavoritesService {

    private FavoritesMapper favoritesMapper;

    public void setFavoritesMapper(FavoritesMapper favoritesMapper) {
        this.favoritesMapper = favoritesMapper;
    }

    @Override
    public int addCollect(Favorites favorites) {
        return this.favoritesMapper.addCollect(favorites);
    }

    @Override
    public Integer judgeCollect(int gid, int uid) {
        return this.favoritesMapper.judgeCollect(gid,uid);
    }


    @Override
    public int cancelCollect(Integer fa_uid, Integer fa_gid) {
        return this.favoritesMapper.cancelCollect(fa_uid,fa_gid);
    }

    @Override
    public int collectionTimes(Integer fa_gid) {
        return this.favoritesMapper.collectionTimes(fa_gid);
    }

    @Override
    public List<Favorites> myCollect(Integer fa_uid) {
        return this.favoritesMapper.myCollect(fa_uid);
    }

    @Override
    public List<Favorites> whoCollect(Integer fa_gid) {
        return this.favoritesMapper.whoCollect(fa_gid);
    }
}
