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
        public int collect(Favorites favorites) {
            return favoritesMapper.collect(favorites);
        }


        @Override
        public int judgeCollect(Integer gid,Integer uid){
           return this.favoritesMapper.judgeCollect(gid,uid);
        }

        @Override
        public int cancel(Integer fa_uid,Integer fa_gid) {
            return favoritesMapper.cancel(fa_uid, fa_gid);
        }

        @Override
        public int countcollect(Integer fa_gid) {
            return favoritesMapper.countcollect(fa_gid);
        }

        @Override
        public List<Favorites> mycollect(Integer fa_uid) {
            return favoritesMapper.mycollect(fa_uid);
        }

        @Override
        public List<Favorites> whocollect(Integer fa_gid) {
            return favoritesMapper.whocollect(fa_gid);
        }
    }
