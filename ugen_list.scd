// these ugens from the standard distribution are still missing:

[ LagControl,
DecodeB2, PanB2, PanB, BiPanB2,
GrainSin, GrainFM, GrainBuf, GrainIn, Warp1,
// ScopeOut, SharedOut, SharedIn,  // mac internal server only : we won't support these ugens for the time being
// DynKlank, DynKlang, HilbertFIR, Tap, IndexL, SelectX, SelectXFocus, TChoose, TWChoose, CompanderD, // fake ugens : we won't implement them right now
Dpoll,
MaxLocalBufs, ClearBuf, SetBuf, LocalBuf,  // these are urgent, but require a bit of extra work
PV_JensenAndersen, PV_MagFreeze, PV_RectComb2, PV_BrickWall, PV_PhaseShift, PV_HainsworthFoote, PV_BinScramble, PV_MagMul, PV_MagDiv, PV_MagSquared, PV_Diffuser, PV_MagSmear, PV_BinWipe, PV_RectComb, PV_RandComb, PV_ConformalMap, PV_RandWipe, PV_BinShift, PV_Max, PV_Copy, PV_CopyPhase, PV_Mul, PV_Add, PV_Div, PV_Min, PV_PhaseShift270, PV_Conj, PV_MagNoise, PV_PhaseShift90, PV_MagShift, PackFFT, UnpackFFT, Unpack1FFT, FFTTrigger,
// AccelerometerX, AccelerometerY, AccelerometerZ // these seem to have disappeared from the standard distrib
QuadN, QuadL, QuadC, CuspN, CuspL, GbmanN, GbmanL, HenonN, HenonL, HenonC, LatoocarfianN, LatoocarfianC, LatoocarfianL, LinCongN, LinCongL, LinCongC, StandardN, StandardL, FBSineN, FBSineL, FBSineC, LorenzL,
Spring, Ball, TBall,
PartConv,
Pluck,
MoogFF

].size
