import classNames from "classnames";
import { useMancalaGame } from "../contexts/MancalaGameContext";

type Props = {
    playerIndex: number,
}

export const PlayerInfo = (props: Props) => {
    const { playerIndex } = props;
    const { gameState } = useMancalaGame();
    const playerName = gameState?.players[playerIndex].name

    return (
        <div
            className={classNames(
                "text-amber-900",
                "font-bold",
                "pt-2",
                "text-center",
                "col-span-2",
                { "col-start-1": playerIndex == 1 },
                { "col-start-5": playerIndex == 0 }
            )}
        >
            Player {playerIndex + 1}: {playerName}
            {gameState?.players[playerIndex].hasTurn &&
                <p className="text-amber-700">It is your turn, {playerName}</p>
            }
        </div>
    )
}