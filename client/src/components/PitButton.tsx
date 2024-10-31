import classNames from "classnames";
import { useMancalaGame } from "../contexts/MancalaGameContext";
import { playMove } from "../services/api";
import { isGameState } from "../types";
import { Alert } from "./Alert";
import { useState } from "react";
import { Stones } from "./Stones";
import '../style/pit.css';
import { ToolTipText } from "./ToolTipText";

type Props = {
    playerIndex: number,
    pitIndex: number;
};


export const PitButton = (props: Props) => {
    const { playerIndex, pitIndex } = props;
    const { gameState, setGameState } = useMancalaGame();
    const [alert, setAlert] = useState<string | null>(null);
    const thisPit = gameState?.players[playerIndex].pits[pitIndex]
    const pitIsPlayable = gameState?.players[playerIndex].hasTurn &&
        thisPit?.nrOfStones != 0


    const onPlayPit = async () => {
        const pitToPlay = playerIndex == 0 ? pitIndex : 7 + pitIndex;
        const result = await playMove(pitToPlay);

        if (isGameState(result)) {
            setGameState(result);
        } else {
            setAlert(`${result.statusCode} ${result.statusText}`);
        }
    }

    return (
        <>
            {alert && <Alert text={alert} onClick={() => setAlert(null)} />}
            <button
                type="button"
                className={classNames(
                    "relative",
                    "rounded border-2",
                    "rounded-[50%]",
                    "border-yellow-800",
                    "bg-[#bc6a3c]",
                    "bg-blend-overlay",
                    "bg-pit",
                    "text-3xl",
                    "font-bold",
                    "leading-normal",
                    "text-amber-900",
                    "transition duration-150",
                    "ease-in-out",
                    "hover:enabled:bg-[#471b07b6]",
                    "hover:enabled:bg-pit",
                    "hover:enabled:bg-blend-overlay",
                    "disabled:border-slate-600",
                    "m-1",
                    { "row-start-2 mb-4": playerIndex == 0 },
                    { "row-start-1 mt-4": playerIndex == 1 },
                ) + " pitButton"
            }
                onClick={onPlayPit}
                disabled={!pitIsPlayable}
            >
                <div
                    className={classNames(
                        "relative",
                        "z-10",
                        "invisible",
                    ) + " pitButtonContent"}  
                >
                    {thisPit?.nrOfStones}
                </div>
                {gameState?.players[playerIndex].hasTurn && thisPit?.nrOfStones == 0 && <ToolTipText/>}
                <Stones nrOfStones={thisPit!.nrOfStones} />
            </button>
        </>
    )
}