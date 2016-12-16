package com.cutesnake.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.cutesnake.game.SnakeGame;

/**
 * Created by heoconxz on 12/12/2016.
 */

public class Snake {
    private Vector3 velocity;
    private Vector3 tempVelocity;
    private Array<Vector3> body;
    private TextureRegion[][] snake;
    public boolean died;


    private Vector3 applePosition;
    private TextureRegion apple;

    float time = 0;
    public Snake()
    {
        velocity = new Vector3(SnakeGame.TILE,0,0);
        tempVelocity = new Vector3(velocity);
        died = false;

        body = new Array<Vector3>();
        body.add(new Vector3(1*SnakeGame.TILE,10*SnakeGame.TILE,0));
        body.add(new Vector3(2*SnakeGame.TILE,10*SnakeGame.TILE,0));
        body.add(new Vector3(3*SnakeGame.TILE,10*SnakeGame.TILE,0));


        snake = TextureRegion.split(SnakeGame.texture,64,64);
        //System.out.println(snake.length +" "+ snake[0].length);
        apple = snake[3][0];
        applePosition = appleSpawn();

    }

    private Vector3 appleSpawn()
    {
        while(true) {
            Vector3 pos =  new Vector3(((int) (Math.random() * (SnakeGame.WIDTH / SnakeGame.TILE))),
                    ((int)(Math.random()*(SnakeGame.HEIGHT/SnakeGame.TILE))),0).scl(SnakeGame.TILE);
            boolean flag = true;
            for (int i = 0; i<body.size; i++)
                if (body.get(i).x == pos.x && body.get(i).y == pos.y) flag=false;
            if (flag) return pos;
        }
    }

    public void death()
    {
        for (int i = 0; i<body.size-1; i++)
        {
            if(body.get(i).x == body.get(body.size-1).x && body.get(i).y == body.get(body.size-1).y)
                died = true;
        }
        // hit the edge
        if (body.get(body.size-1).x < 0||body.get(body.size-1).x >= SnakeGame.WIDTH
            ||body.get(body.size-1).y < 0||body.get(body.size-1).y >= SnakeGame.HEIGHT)
            died = true;
    }

    public void update(float dt){

        time+=dt;
        //System.out.println(time);

        if (time > 0.2) {
            velocity.set(tempVelocity);
            if (body.get(body.size - 1).x+velocity.x == applePosition.x &&
                    body.get(body.size - 1).y+velocity.y == applePosition.y )
            {
                body.add(applePosition);
                applePosition = appleSpawn();
            }
            else{
                time=0;
                for (int i = 0; i < body.size - 1; i++) {
                    body.get(i).set(body.get(i + 1));
                }
                body.get(body.size - 1).add(velocity);
                death();
            }

        }
    }

    public void render(SpriteBatch sb) {
        for (int i=body.size-1; i>=0; i--) {
            int tx=0;
            int ty=0;
            float x = body.get(i).x;
            float y = body.get(i).y;
            float px,py,nx,ny;
            if (i==body.size-1){
                px = body.get(body.size-2).x;
                py = body.get(body.size-2).y;
                if (y > py){
                    //Up
                    tx=3; ty=0;
                }else if (x > px) {
                    // Right
                    tx = 4; ty = 0;
                } else if (y<py ) {
                    // Down
                    tx = 4; ty = 1;
                } else if (x< px) {
                    // Left
                    tx = 3; ty = 1;
                }
            } else if (i==0)
            {
                nx = body.get(1).x;
                ny = body.get(1).y;
                if (ny > y) {
                    // Up
                    tx = 3; ty = 2;
                } else if (nx > x) {
                    // Right
                    tx = 4; ty = 2;
                } else if (ny < y) {
                    // Down
                    tx = 4; ty = 3;
                } else if (nx < x) {
                    // Left
                    tx = 3; ty = 3;
                }
            } else{
                px = body.get(i-1).x;
                py = body.get(i-1).y;
                nx = body.get(i+1).x;
                ny = body.get(i+1).y;

                if (nx < x && px > x || px < x && nx > x) {
                    // Horizontal Left-Right
                    tx = 1; ty = 0;
                } else if (nx < x && py < y || px < x && ny < y) {
                    // Angle Left-Down
                    tx = 2; ty = 0;
                } else if (ny < y && py > y || py < y && ny > y) {
                    // Vertical Up-Down
                    tx = 2; ty = 1;
                } else if (ny > y && px < x || py > y && nx < x) {
                    // Angle Top-Left
                    tx = 2; ty = 2;
                } else if (nx > x && py > y || px > x && ny > y) {
                    // Angle Right-Up
                    tx = 0; ty = 1;
                } else if (ny < y && px > x || py < y && nx > x) {
                    // Angle Down-Right
                    tx = 0; ty = 0;
                }
            }

            sb.draw(snake[ty][tx], body.get(i).x, body.get(i).y);

        }
        sb.draw(apple,applePosition.x,applePosition.y);
    }
    public void changeDirection(int x,int y)
    {
        //System.out.println("button pressed");
        if ((velocity.x == 0 && y==0)||(velocity.y==0 && x==0))
            tempVelocity.set((float)x,(float)y,0).scl(SnakeGame.TILE);
    }

    public Vector3 getVelocity() {
        return velocity;
    }
    public void dispose()
    {
    }
}
