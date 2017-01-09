package JumpAndRun;
public class Camera
{
	float x;
	float speed;

	public Camera()
	{
		x = 0;
		speed = 0.1f;
	}

	public void move(long delta)
	{
		x -= speed * (delta / 1e6);
	}
}
