package com.rmv.oop.dtw.algotithm;

import lombok.Getter;

@Getter
public class DTWResult {
    private final int[][] warpingPath;
    private final double distance;

    public DTWResult(final int[][] warpingPath, final double distance) {
        this.warpingPath = warpingPath;
        this.distance = distance;
    }
}
