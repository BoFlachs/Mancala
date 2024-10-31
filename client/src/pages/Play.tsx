import classNames from "classnames";
import { MancalaBoard } from "../components/MancalaBoard";
import { useMancalaGame } from "../contexts/MancalaGameContext";
import { PlayerInfo } from "../components/PlayerInfo";
import { WinnerMessage } from "../components/WinnerMessage";

export const Play = () => {
    const { gameState } = useMancalaGame();


    return (
        <>
            <div
                className={classNames(
                    "grid",
                    "grid-cols-6",
                    "grid-rows-5",
                    "mt-16",
                    "m-auto",
                    "w-[800px]",
                )}
            >
                <MancalaBoard />
                {!gameState?.gameStatus.endOfGame &&
                    <>
                        <PlayerInfo playerIndex={1} />
                        <PlayerInfo playerIndex={0} />
                    </>}
            </div>
            {gameState?.gameStatus.endOfGame && <WinnerMessage />}
        </>
    )
};