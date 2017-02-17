package com.mtyoung.persistence;

import com.mtyoung.entity.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/15/17.
 */
public class StateDaoTest {

    State state;
    StateDao dao;
    int newState = 0;

    @Before
    public void setup() {
        dao = new StateDao();
        state = new State();

        state.setLong("CHICKEN");
        state.setShort("CZ");
    }

    @After
    public void cleanup() {
        if (newState != 0) {
            dao.deleteState(newState);
        }
    }


    @Test
    public void getAllStates() throws Exception {
        newState = dao.addState(state);
        List<State> states = dao.getAllStates();
        assertTrue("no states returned", states.size() > 0);
    }

    @Test
    public void getState() throws Exception {
        newState = dao.addState(state);
        assertEquals("state id not returned", state.getIdstate(), dao.getState(newState).getIdstate());
        assertEquals("state long not returned", state.getLongname(), dao.getState(newState).getLongname());
        assertEquals("state short not returned", state.getShortname(), dao.getState(newState).getShortname());
    }

    @Test
    public void addState() throws Exception {
        newState = dao.addState(state);
        assertEquals("state id not inserted", state.getIdstate(), dao.getState(newState).getIdstate());
        assertEquals("state long not inserted", state.getLongname(), dao.getState(newState).getLongname());
        assertEquals("state short not inserted", state.getShortname(), dao.getState(newState).getShortname());
    }

    @Test
    public void deleteState() throws Exception {
        dao.addState(state);
        dao.deleteState(state.getIdstate());
        assertNull("state not deleted", dao.getState(state.getIdstate()));
    }

    @Test
    public void updateState() throws Exception {
        newState = dao.addState(state);

        state.setLong("BOBOBO");
        state.setShort("BO");

        dao.updateState(state);

        assertEquals("state id not updated", state.getIdstate(), dao.getState(newState).getIdstate());
        assertEquals("state long not updated", state.getLongname(), dao.getState(newState).getLongname());
        assertEquals("state short not updated", state.getShortname(), dao.getState(newState).getShortname());

    }

}