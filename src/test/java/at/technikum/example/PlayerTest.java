package at.technikum.example;

import at.technikum.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;




class PlayerTest {



    @Test
    public void testGetMarker() {
        Player player = new Player("Alice", 'X');
        assertEquals('X', player.getMarker());
    }

    @Test
    public void testGetName() {
        Player player = new Player("Bob", 'O');
        assertEquals("Bob", player.getName());
    }
}

