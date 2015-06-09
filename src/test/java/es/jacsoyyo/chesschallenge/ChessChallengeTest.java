/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge;

import static org.assertj.core.api.Assertions.*;
import org.junit.Ignore;

import org.junit.Test;


/**
 *
 * @author jacobo
 */
public class ChessChallengeTest {
    
    public ChessChallengeTest() {
    }
    
    @Ignore("Too long for a unit test")
    @Test
    public void testMainWihNoArguments() {
        String[] args = {};
        ChessChallenge.main(args);
    }
    
    @Test
    public void test1King1x1board(){
        String[] args = {"1", "1", "K"};
        ChessChallenge.main(args);
    }

    @Test
    public void test4Rooks3x3board(){
        String[] args = {"4", "4", "R,R,R,R"};
        ChessChallenge.main(args);
    }
}
