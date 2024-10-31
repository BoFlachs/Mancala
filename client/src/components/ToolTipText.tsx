import classNames from "classnames"

export const ToolTipText = () => {

    return (
        <div
            className={classNames(
                "invisible",
                "text-xs",
                "bg-[#f0f7dabe]",
                "text-center",
                "rounded-md",
                "absolute",
                "z-[1]",
                "bottom-[70%]",
                "opacity-0",
                "transition-opacity",
                "delay-500",
                "duration-200",
                "ease-in-out"
            ) + " toolTipText"}
        >
            You cannot play an empty pit
        </div>

    )

}