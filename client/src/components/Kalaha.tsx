import classNames from "classnames";
import { useMancalaGame } from "../contexts/MancalaGameContext";
import { Alert } from "./Alert";
import { useState } from "react";
import { Stones } from "./Stones";

type Props = {
    playerIndex: number
    kalahaIndex: number,
}

export const Kalaha = (props: Props) => {
    const { playerIndex, kalahaIndex } = props;
    const { gameState } = useMancalaGame();
    const [alert, setAlert] = useState<string | null>(null);
    const thisKalaha = gameState?.players[playerIndex].pits[kalahaIndex]

    return (
        <>
            {alert && <Alert text={alert} onClick={() => setAlert(null)} />}
            <div
                className={classNames(
                    "relative",
                    "rounded-[50%]",
                    "h-[90%]",
                    "w-[70%]",
                    "rounded border-2",
                    "border-yellow-800",
                    "bg-[#bc6a3c]",
                    "bg-blend-overlay",
                    "bg-pit",
                    "content-center",
                    "text-center",
                    "text-6xl",
                    "font-bold",
                    "text-amber-900",
                    "w-28",
                    "my-2",
                    "row-span-2",
                    "col-span-2",
                    "place-self-center",
                    { "mr-8": playerIndex == 0 },
                    { "row-start-1 ml-8": playerIndex == 1 }
                )}
            >
                <div
                    className={classNames(
                        "relative",
                        "z-10",
                    )}  
                >
                    {thisKalaha!.nrOfStones}
                </div>
                <Stones nrOfStones={thisKalaha!.nrOfStones} />
            </div>
        </>
    )
}