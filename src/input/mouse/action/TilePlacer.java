package input.mouse.action;

import core.Position;
import game.Game;
import map.GameMap;
import map.Tile;
import state.State;
import ui.UIImage;

public class TilePlacer extends MouseAction {
    private Tile tile;
    private UIImage preview;
    private int gridX;
    private int gridY;

    public TilePlacer(Tile tile) {
        this.tile = tile;
        preview = new UIImage(tile.getSprite());
    }

    @Override
    public void onClick(State state) {
    }

    @Override
    public void onDrag(State state) {
        if (state.getGameMap().gridWithinBounds(gridX, gridY)) {
            state.getGameMap().setTile(gridX, gridY, Tile.copyOf(tile));
        }

        if (state.getGameSettings().getEditorSettings().getAutoTile().getValue()) {
            autoTile(state);
        }
    }

    private void autoTile(State state) {
        GameMap gameMap = state.getGameMap();
        for (int x=gridX-1; x<gridX+2; x++) {
            for (int y=gridY-1; y<gridY+2; y++) {
                if (gameMap.gridWithinBounds(x, y)) {
                    Tile currentTile = gameMap.getTiles()[x][y];
                    int index = 6;
                    switch (getNeighborTiles(gameMap, x, y)) {
                        case "001011111": case "000011011": case "001011011": case "000011111" :                    index = 0; break;
                        case "000111111": case "100111111": case "001111111": case "101111111" :                    index = 1; break;
                        case "000110110": case "100110110": case "000110111": case "100110111": case "101111110" :  index = 2; break;
                        case "011011011": case "011011111": case "111011011": case "111011111" :                    index = 5; break;
                        case "110110110": case "110110111": case "111110110": case "111110111" :                    index = 7; break;
                        case "011011000": case "111011000": case "011011001": case "111011001": case "011111101" :  index = 10; break;
                        case "111111000": case "111111001": case "111111100": case "111111101" :                    index = 11; break;
                        case "110110000": case "111110000": case "110110100": case "111110100" :                    index = 12; break;
                        case "111111110" :                                                                          index = 15; break;
                        case "111111011" :                                                                          index = 16; break;
                        case "110111011" :                                                                          index = 17; break;
                        case "110111111" :                                                                          index = 20; break;
                        case "011111111" :                                                                          index = 21; break;
                        case "011111110" :                                                                          index = 22; break;
                        default :                                                                                   index = 6;
                    };

                    Tile indexedTile = Tile.copyOf(currentTile);
                    indexedTile.setTileIndex(index);
                    state.getGameMap().setTile(x, y, indexedTile);
                }
            }
        }
    }

    private String getNeighborTiles(GameMap gameMap, int gridX, int gridY) {
        StringBuilder stringBuilder = new StringBuilder();
        Tile currentTile = gameMap.getTiles()[gridX][gridY];

        for (int x=gridX-1; x<gridX+2; x++) {
            for (int y=gridY-1; y<gridY+2; y++) {
                if (!gameMap.gridWithinBounds(x, y)) {
                    stringBuilder.append(1);
                    continue;
                }

                if (gameMap.getTiles()[x][y].getTileName().equals(currentTile.getTileName())) {
                    stringBuilder.append(1);
                } else {
                    stringBuilder.append(0);
                }
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public void update(State state) {
        Position position = Position.copyOf(state.getInput().getMousePosition());
        position.add(state.getCamera().getPosition());
        gridX = position.intX() / Game.SPRITE_SIZE;
        gridY = position.intY() / Game.SPRITE_SIZE;

        position.subtract(new Position(position.intX() % Game.SPRITE_SIZE, position.intY() % Game.SPRITE_SIZE));
        position.subtract(state.getCamera().getPosition());

        preview.setAbsolutePosition(position);
    }

    @Override
    public UIImage getSprite() {
        return preview;
    }
}
