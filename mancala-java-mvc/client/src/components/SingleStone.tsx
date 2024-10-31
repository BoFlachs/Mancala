import classNames from "classnames";
import stone from "../assets/stone.png"

type Props = {
    left: boolean,
    top: boolean,
}

export const SingleStone = (props: Props) => {
    const { left, top} = props;

    function getRandomAngle(): number {
        return Math.floor(Math.random() * (360))
    }
    function getRandomSize(): number {
        return Math.random() * (0.6 - 0.4) + 0.4
    }
    function getRandomXPosition(): number {
        return Math.floor(Math.random() * (25))
    }
    function getRandomYPosition(): number {
        return Math.floor(Math.random() * (45))
    }

    if (left && top) {
        return (
            <img src={stone}
                className={classNames(
                    "absolute",
                    "z-0"
                )}
                style={{
                    transform: `rotate(${getRandomAngle()}deg)
                scale(${getRandomSize()})`,
                    left: `${getRandomXPosition()}%`,
                    top: `${getRandomYPosition()}%`,
                }}
            />
        )
    } else if (!left && top){
        return (
            <img src={stone}
                className={classNames(
                    "absolute",
                )}
                style={{
                    transform: `rotate(${getRandomAngle()}deg)
                scale(${getRandomSize()})`,
                    right: `${getRandomXPosition()}%`,
                    top: `${getRandomYPosition()}%`,
                }}
            />
        )
    } else if (left && !top){
        return (
            <img src={stone}
                className={classNames(
                    "absolute",
                )}
                style={{
                    transform: `rotate(${getRandomAngle()}deg)
                scale(${getRandomSize()})`,
                    left: `${getRandomXPosition()}%`,
                    bottom: `${getRandomYPosition()}%`,
                }}
            />
        )
    } else {
        return (
            <img src={stone}
                className={classNames(
                    "absolute",
                )}
                style={{
                    transform: `rotate(${getRandomAngle()}deg)
                scale(${getRandomSize()})`,
                    right: `${getRandomXPosition()}%`,
                    bottom: `${getRandomYPosition()}%`,
                }}
            />
        )
    }

}