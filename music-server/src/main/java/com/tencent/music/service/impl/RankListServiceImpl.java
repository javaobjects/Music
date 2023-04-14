package com.tencent.music.service.impl;

import com.tencent.music.dao.RankListMapper;
import com.tencent.music.domain.RankList;
import com.tencent.music.service.RankListService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class RankListServiceImpl implements RankListService {

    private static final Logger logger = LogManager.getLogger(RankListServiceImpl.class);

    @Autowired
    private RankListMapper rankMapper;

    @Override
    public boolean addRank(RankList rankList) {
        return rankMapper.insertSelective(rankList) > 0;
    }

    @Override
    public int rankOfSongListId(Long songListId) {
        // 评分总人数如果为 0，则返回0；否则返回计算出的结果
        int rankNum = rankMapper.selectRankNum(songListId);
        return (rankNum <= 0) ? 0 : rankMapper.selectScoreSum(songListId) / rankNum;
    }

    @Override
    public int getUserRank(Long consumerId, Long songListId) {
        return rankMapper.selectUserRank(consumerId, songListId);
    }
}
